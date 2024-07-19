package com.bionickhand.vknewsclient.domain

import com.bionickhand.vknewsclient.R

data class CommentPost(
    val id: Int,
    val authorName: String = "Author",
    val authorAvatarImageId: Int = R.drawable.comment_author_avatar,
    val commentText: String = "Long comment text",
    val publishedDate: String = "14:00"
)
