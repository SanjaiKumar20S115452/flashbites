package com.sanjai.newsappdemo.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration

import com.sanjai.newsappdemo.data.mappers.toArticle
import com.sanjai.newsappdemo.data.mappers.toArticleListing
import com.sanjai.newsappdemo.data.model.world_news.Article
import com.sanjai.newsappdemo.data.model.world_news.SaveArticle
import com.sanjai.newsappdemo.data.model.world_news.UserData
import com.sanjai.newsappdemo.data.util.Constants.ARTICLE_REF
import com.sanjai.newsappdemo.data.util.Resource
import com.sanjai.newsappdemo.domain.model.world_news.ArticleListing
import com.sanjai.newsappdemo.domain.repository.NewsLocalDataSource
import io.grpc.internal.SharedResourceHolder
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.internal.artificialFrame

class NewsLocalDataSourceImpl(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore
): NewsLocalDataSource {

    private val articleRef: CollectionReference = db.collection(ARTICLE_REF)

    override val userId: String
        get() = auth.currentUser!!.uid

    override suspend fun saveNewsArticles(article: Article,onNewsSaved: (Boolean) -> Unit) {
        val articleId = articleRef.document().id
        val articles = SaveArticle(
            articleId = articleId,
            article = article,
            userId = userId
        )
        articleRef.document(articleId)
            .set(articles)
            .addOnSuccessListener {
                onNewsSaved(true)
            }
            .addOnFailureListener {
                onNewsSaved(false)
            }
    }

    override suspend fun deleteArticle(articleId: String,onDeleteArticle: (Boolean) -> Unit) {
        articleRef.document(articleId).delete().addOnCompleteListener {
            onDeleteArticle(it.isSuccessful)
        }
    }

    override suspend fun deleteAllSavedArticles(
        userId: String,
        onDeleteAllCompleted: (Boolean) -> Unit
    ) {
        articleRef.whereEqualTo("userId",userId)
            .addSnapshotListener { snapShot, e ->
                if(snapShot != null) {
                    val savedArticles = snapShot.toObjects(SaveArticle::class.java)
                    savedArticles.forEach {
                        articleRef.document().delete()
                            .addOnCompleteListener { task ->
                                if(task.isSuccessful) {
                                    onDeleteAllCompleted(true)
                                }else {
                                    onDeleteAllCompleted(false)
                                }
                            }
                    }
                }
            }
    }

    override fun getAllSavedNewsArticles(userId: String): Flow<Resource<List<SaveArticle>>> {
        return callbackFlow {
            var snapShotStateListener: ListenerRegistration? = null
            try {
                snapShotStateListener = articleRef
                    .whereEqualTo("userId",userId)
                    .addSnapshotListener { snapShot, e ->
                        val response = if(snapShot != null) {
                            val savedNotes = snapShot.toObjects(SaveArticle::class.java)
                            Resource.Success(data = savedNotes)
                        }else {
                            Resource.Error(message = e?.localizedMessage ?: "Error Fetching details!")
                        }
                        trySend(response)
                    }
            }catch (e: Exception) {
                trySend(Resource.Error(message = e.message ?: ""))
            }
            awaitClose {
                snapShotStateListener?.remove()
            }
        }
    }

}