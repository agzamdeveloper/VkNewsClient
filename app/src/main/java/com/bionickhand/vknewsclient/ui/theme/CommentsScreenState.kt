package com.bionickhand.vknewsclient.ui.theme

import com.bionickhand.vknewsclient.domain.CommentPost
import com.bionickhand.vknewsclient.domain.FeedPost

sealed class CommentsScreenState {
    data object Initial: CommentsScreenState()

    data class CommentsState(
        val feedPost: FeedPost,
        val comments: List<CommentPost>
    ): CommentsScreenState()

}