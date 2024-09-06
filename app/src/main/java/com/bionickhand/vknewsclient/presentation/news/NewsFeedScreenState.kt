package com.bionickhand.vknewsclient.presentation.news

import com.bionickhand.vknewsclient.domain.FeedPost

sealed class NewsFeedScreenState {
    data object Initial: NewsFeedScreenState()

    data class PostsState(
        val posts: List<FeedPost>,
        val nextDataIsLoading: Boolean = false
    ): NewsFeedScreenState()

}