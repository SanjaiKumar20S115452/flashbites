package com.sanjai.newsappdemo.data.repository

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.ktx.toObject
import com.sanjai.newsappdemo.data.model.world_news.UserData
import com.sanjai.newsappdemo.data.util.Constants.USERS
import com.sanjai.newsappdemo.domain.repository.AuthRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class AuthRepositoryImpl(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore
): AuthRepository {

    private val usersRef: CollectionReference = db.collection(USERS)

    override val currentUser: Boolean
        get() = auth.currentUser != null

    override val userId: String?
        get() = auth.currentUser?.uid

    override suspend fun login(
        email: String,
        password: String,
        onLoginCompleted: (Boolean) -> Unit,
        loginException: (String) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if(task.isSuccessful) {
                    onLoginCompleted(true)
                }else {
                    onLoginCompleted(false)
                }
            }
            .addOnFailureListener {
                loginException(it.localizedMessage ?: "Unknown error occurred while logging in!")
            }
    }

    override suspend fun signUp(
        email: String,
        password: String,
        username: String,
        age: Int,
        usernameAlreadyExists: (Boolean) -> Unit,
        onSignUpCompleted: (Boolean) -> Unit,
        signUpException: (String) -> Unit
    ) {
        usersRef
            .whereEqualTo("username",username)
            .get()
            .addOnSuccessListener {
                if(it.size() > 0) {
                    usernameAlreadyExists(true)
                }else {
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if(task.isSuccessful) {
                                onSignUpCompleted(true)
                                createOrUpdateUserData(email, username, age)
                            }else {
                                onSignUpCompleted(false)
                            }
                        }
                }
            }
            .addOnFailureListener {
                signUpException(it.localizedMessage ?: "Unknown error occurred while signing in!")
            }
    }

    private fun createOrUpdateUserData(
        email: String,
        username: String,
        age: Int
    ) {
        val userIds = auth.currentUser!!.uid
        val userData = UserData(
            userIds, email, username, age
        )
        usersRef.document(userIds)
            .get()
            .addOnSuccessListener {
                if(it.exists()) {
                    usersRef.document(userIds).update(userData.toMap())
                }else {
                    usersRef.document(userIds).set(userData)
                    getUserData(userIds)
                }
            }
    }

    override val userData: MutableState<String> = mutableStateOf("")

    private fun getUserData(
        userId: String
    ) {
        usersRef.document(userId)
            .get()
            .addOnSuccessListener {
                val user = it.toObject<UserData>()
                if (user != null) {
                    userData.value = user.username!!
                }
            }
    }

    override suspend fun signOut() {
        auth.signOut()
    }

    override fun getSignedInUserDetails(userId: String): Flow<com.sanjai.newsappdemo.data.util.Resource<UserData>> {
        return callbackFlow {
            var snapShotListener: ListenerRegistration? = null
            try {
                snapShotListener = usersRef
                    .document(userId)
                    .addSnapshotListener { snapShot, e ->
                        val response = if(snapShot != null) {
                            val userData = snapShot.toObject(UserData::class.java)
                            com.sanjai.newsappdemo.data.util.Resource.Success(data = userData)
                        }else {
                            com.sanjai.newsappdemo.data.util.Resource.Error(
                                e?.message ?: "Unknown error has occurred!"
                            )
                        }
                        trySend(response)
                    }
            }catch (e: Exception) {
                trySend(com.sanjai.newsappdemo.data.util.Resource.Error(e.message ?: ""))
            }
            awaitClose {
                snapShotListener?.remove()
            }
        }
    }
}