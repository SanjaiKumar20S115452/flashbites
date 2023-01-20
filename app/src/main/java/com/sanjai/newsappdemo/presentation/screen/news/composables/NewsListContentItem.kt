package com.sanjai.newsappdemo.presentation.screen.news.composables

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha.medium
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sanjai.newsappdemo.R
import com.sanjai.newsappdemo.data.mappers.toArticle
import com.sanjai.newsappdemo.domain.model.world_news.ArticleListing
import com.sanjai.newsappdemo.presentation.screen.news.NewsEvent
import com.sanjai.newsappdemo.ui.theme.HeaderColor
import com.sanjai.newsappdemo.ui.theme.reddish
import com.sanjai.newsappdemo.ui.theme.sourceFontFamily

@SuppressLint("RememberReturnType")
@Composable
fun ArticleListItem(
    articleListing: ArticleListing,
    onArticleClicked: (String, String) -> Unit,
    contentScale: ContentScale = ContentScale.Crop,
    onEvent: (NewsEvent) -> Unit
) {
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT,"${articleListing.url}")
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent,null)
    val context = LocalContext.current
    Surface(
        modifier = Modifier
            .padding(start = 15.dp, end = 15.dp, top = 15.dp)
            .clickable {
                onArticleClicked(articleListing.url!!, articleListing.source!!.name!!)
            },
        color = Color.DarkGray.copy(alpha = 0.4f),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            AsyncImage(
                model = ImageRequest
                    .Builder(LocalContext.current)
                    .data("${articleListing.urlToImage}")
                    .crossfade(true)
                    .crossfade(400)
                    .error(R.drawable.warning)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .height(130.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier
                    .padding(end = 10.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = reddish,
                            fontFamily = sourceFontFamily,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("SOURCE:   ")
                        withStyle(style = SpanStyle(
                            color = HeaderColor,
                            fontFamily = sourceFontFamily,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                        ) {
                            append("${articleListing.source?.name}")
                        }
                    }
                },
                    modifier = Modifier
                        .padding(start = 5.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painterResource(id = R.drawable.ic_share),
                        contentDescription = null,
                        modifier = Modifier
                            .size(16.dp)
                            .clickable {
                                context.startActivity(shareIntent)
                            }
                    )
                    Spacer(modifier = Modifier.width(11.dp))
                    Image(
                        painterResource(id = R.drawable.ic_fav_news),
                        contentDescription = null,
                        modifier = Modifier
                            .size(16.dp)
                            .clickable {
                                onEvent(NewsEvent.OnSaveNewsClicked(articleListing.toArticle()))
                            }
                    )
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
            Column(
                modifier  = Modifier
                    .padding(all = 5.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = articleListing.title!!,
                    fontSize = 13.sp,
                    fontFamily = sourceFontFamily,
                    color = HeaderColor
                )
                Spacer(modifier = Modifier.height(5.dp))
                articleListing.content?.let {
                    Text(
                        text = it,
                        fontFamily = sourceFontFamily,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Medium,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        color = HeaderColor.copy(alpha = medium)
                    )
                }
            }
        }
    }
}