package com.bionickhand.vknewsclient.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bionickhand.vknewsclient.R
import com.bionickhand.vknewsclient.domain.FeedPost
import com.bionickhand.vknewsclient.domain.StatisticItem
import com.bionickhand.vknewsclient.domain.StatisticType

@Composable
fun PostCard(
    modifier: Modifier = Modifier,
    feedPost: FeedPost,
    onViewsClickListener: (StatisticItem) -> Unit,
    onCommentsClickListener: (StatisticItem) -> Unit,
    onSharesClickListener: (StatisticItem) -> Unit,
    onLikesClickListener: (StatisticItem) -> Unit
) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            PostHeader(feedPost)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = feedPost.contentText
            )
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                painter = painterResource(id = feedPost.contentImageResId),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
            Spacer(modifier = Modifier.height(8.dp))
            Statistics(
                statistics = feedPost.statistics,
                onViewsClickListener = onViewsClickListener,
                onCommentsClickListener = onCommentsClickListener,
                onSharesClickListener = onSharesClickListener,
                onLikesClickListener = onLikesClickListener
            )
        }
    }
}

@Composable
fun PostHeader(
    feedPost: FeedPost
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .clip(CircleShape)
                .size(50.dp),
            painter = painterResource(id = feedPost.avatarResId),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = feedPost.communityName,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = feedPost.publicationDate,
                color = MaterialTheme.colorScheme.onSecondary
            )
        }
        Icon(
            imageVector = Icons.Rounded.MoreVert,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSecondary
        )
    }
}

@Composable
fun Statistics(
    statistics: List<StatisticItem>,
    onViewsClickListener: (StatisticItem) -> Unit,
    onCommentsClickListener: (StatisticItem) -> Unit,
    onSharesClickListener: (StatisticItem) -> Unit,
    onLikesClickListener: (StatisticItem) -> Unit
) {
    Row {
        Row(
            modifier = Modifier.weight(1f)
        ) {
            val viewsItem = statistics.getItemByType(StatisticType.VIEWS)
            IconWithText(
                id = R.drawable.ic_views_count,
                text = viewsItem.count.toString(),
                onClickListener = {
                    onViewsClickListener(viewsItem)
                }
            )
        }
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val sharesItem = statistics.getItemByType(StatisticType.SHARES)
            IconWithText(
                id = R.drawable.ic_share,
                text = sharesItem.count.toString(),
                onClickListener = {
                    onSharesClickListener(sharesItem)
                }
            )
            val commentsItem = statistics.getItemByType(StatisticType.COMMENTS)
            IconWithText(
                id = R.drawable.ic_comment,
                text = commentsItem.count.toString(),
                onClickListener = {
                    onCommentsClickListener(commentsItem)
                }
            )
            val likesItem = statistics.getItemByType(StatisticType.LIKES)
            IconWithText(
                id = R.drawable.ic_like,
                text = likesItem.count.toString(),
                onClickListener = {
                    onLikesClickListener(likesItem)
                }
            )
        }
    }
}

private fun List<StatisticItem>.getItemByType(type: StatisticType): StatisticItem {
    return this.find { it.type == type } ?: throw IllegalStateException()
}

@Composable
fun IconWithText(
    id: Int,
    text: String,
    onClickListener : () -> Unit
) {
    Row(
        modifier = Modifier.clickable {
            onClickListener()
        },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = id),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSecondary
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSecondary
        )
    }
}