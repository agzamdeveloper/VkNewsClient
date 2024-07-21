package com.bionickhand.vknewsclient.ui.theme

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bionickhand.vknewsclient.domain.CommentPost
import com.bionickhand.vknewsclient.domain.FeedPost

class CommentsViewModel : ViewModel() {

    private val _screenState = MutableLiveData<CommentsScreenState>(CommentsScreenState.Initial)
    val screenState: LiveData<CommentsScreenState> = _screenState

    init {
        loadComments(FeedPost())
    }

    fun loadComments(feedPost: FeedPost) {
        val comments = mutableListOf<CommentPost>().apply {
            repeat(10) {
                add(
                    CommentPost(id = it)
                )
            }
        }
        _screenState.value = CommentsScreenState.CommentsState(feedPost, comments)
    }
}