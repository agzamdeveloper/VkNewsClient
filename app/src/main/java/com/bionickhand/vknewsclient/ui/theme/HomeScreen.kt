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
import com.bionickhand.vknewsclient.MainViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    viewModel: MainViewModel
){
    val feedPosts = viewModel.feedPosts.observeAsState(listOf())
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
            items = feedPosts.value,
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
                        onCommentsClickListener = viewModel::changeStatisticItem,
                        onLikesClickListener = viewModel::changeStatisticItem
                    )
                }
            )
        }
    }
}