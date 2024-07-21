package com.bionickhand.vknewsclient.ui.theme

import com.bionickhand.vknewsclient.domain.FeedPost

sealed class PostScreenState {
    data object Initial: PostScreenState()

    data class PostsState(val posts: List<FeedPost>): PostScreenState()

}