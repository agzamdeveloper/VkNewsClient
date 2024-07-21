package com.bionickhand.vknewsclient.ui.theme

import com.bionickhand.vknewsclient.domain.CommentPost
import com.bionickhand.vknewsclient.domain.FeedPost

sealed class HomeScreenState {
    data object Initial: HomeScreenState()

    data class FeedPostsState(val posts: List<FeedPost>): HomeScreenState()

    data class CommentsState(
        val feedPost: FeedPost,
        val comments: List<CommentPost>
    ): HomeScreenState()
}