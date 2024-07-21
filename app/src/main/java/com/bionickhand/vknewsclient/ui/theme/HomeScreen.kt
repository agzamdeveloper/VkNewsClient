package com.bionickhand.vknewsclient.ui.theme

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bionickhand.vknewsclient.FeedPostViewModel
import com.bionickhand.vknewsclient.domain.FeedPost

@Composable
fun HomeScreen(
    onCommentsClickListener: (FeedPost) -> Unit
) {
    val viewModel: FeedPostViewModel = viewModel()
    val screenState = viewModel.screenState.observeAsState(PostScreenState.Initial)
    when (val currentState = screenState.value) {
        is PostScreenState.PostsState -> {
            FeedPosts(
                viewModel = viewModel,
                posts = currentState.posts,
                onCommentsClickListener = onCommentsClickListener
            )
        }

        PostScreenState.Initial -> {}
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun FeedPosts(
    viewModel: FeedPostViewModel,
    posts: List<FeedPost>,
    onCommentsClickListener: (FeedPost) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(
            top = 16.dp,
            start = 8.dp,
            end = 8.dp,
            bottom = 72.dp
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = posts,
            key = { it.id }
        ) { feedPost ->
            val dismissState = rememberSwipeToDismissBoxState()

            if (dismissState.currentValue == SwipeToDismissBoxValue.EndToStart) {
                viewModel.deleteFeedPost(feedPost)
            }

            SwipeToDismissBox(
                modifier = Modifier.animateItemPlacement(),
                state = dismissState,
                backgroundContent = {},
                enableDismissFromStartToEnd = false,
                content = {
                    PostCard(
                        feedPost = feedPost,
                        onViewsClickListener = viewModel::changeStatisticItem,
                        onSharesClickListener = viewModel::changeStatisticItem,
                        onCommentsClickListener = {
                            onCommentsClickListener(feedPost)
                        },
                        onLikesClickListener = viewModel::changeStatisticItem
                    )
                }
            )
        }
    }
}