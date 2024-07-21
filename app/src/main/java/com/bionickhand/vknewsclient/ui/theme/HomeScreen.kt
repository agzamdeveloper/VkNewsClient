package com.bionickhand.vknewsclient.ui.theme

import androidx.activity.compose.BackHandler
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
import com.bionickhand.vknewsclient.MainViewModel
import com.bionickhand.vknewsclient.domain.FeedPost

@Composable
fun HomeScreen(
    viewModel: MainViewModel
) {
    val screenState = viewModel.screenState.observeAsState(HomeScreenState.Initial)
    when(val currentState = screenState.value){
        is HomeScreenState.FeedPostsState -> {
            FeedPosts(viewModel = viewModel, posts = currentState.posts)
        }

        is HomeScreenState.CommentsState -> {
            CommentScreen(
                feedPost = currentState.feedPost,
                comments = currentState.comments,
                onBackPressed = { viewModel.closeComments() }
            )
            BackHandler {
                viewModel.closeComments()
            }
        }

        HomeScreenState.Initial -> {

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun FeedPosts(
    viewModel: MainViewModel,
    posts: List<FeedPost>
){
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
                            viewModel.showComments(feedPost)
                        },
                        onLikesClickListener = viewModel::changeStatisticItem
                    )
                }
            )
        }
    }
}