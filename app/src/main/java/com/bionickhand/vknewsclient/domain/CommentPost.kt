package com.bionickhand.vknewsclient.domain

data class CommentPost(
    val id: Long,
    val authorName: String,
    val authorAvatarUrl: String,
    val commentText: String,
    val publishedDate: String
)
