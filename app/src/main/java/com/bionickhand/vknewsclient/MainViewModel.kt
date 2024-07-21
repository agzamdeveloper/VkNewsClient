package com.bionickhand.vknewsclient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bionickhand.vknewsclient.domain.CommentPost
import com.bionickhand.vknewsclient.domain.FeedPost
import com.bionickhand.vknewsclient.domain.StatisticItem
import com.bionickhand.vknewsclient.ui.theme.HomeScreenState

class MainViewModel : ViewModel() {
    private val comments = mutableListOf<CommentPost>().apply {
        repeat(10){
            add(
                CommentPost(id = it)
            )
        }
    }

    private val initialList = mutableListOf<FeedPost>().apply {
        repeat(100) {
            add(
                FeedPost(id = it)
            )
        }
    }
    private val initialState = HomeScreenState.FeedPostsState(initialList)

    private val _screenState = MutableLiveData<HomeScreenState>(initialState)
    val screenState: LiveData<HomeScreenState> = _screenState

    private var savedState: HomeScreenState? = HomeScreenState.Initial

    fun showComments(feedPost: FeedPost){
        savedState = _screenState.value
        _screenState.value = HomeScreenState.CommentsState(feedPost, comments)
    }

    fun closeComments(){
        _screenState.value = savedState
    }

    fun changeStatisticItem(feedPost: FeedPost, item: StatisticItem) {
        val currentState = screenState.value
        if (currentState !is HomeScreenState.FeedPostsState) return

        val oldPosts = currentState.posts.toMutableList()
        val oldStatistics = feedPost.statistics
        val newStatistics = oldStatistics.toMutableList().apply {
            replaceAll { oldItem ->
                if (oldItem.type == item.type) {
                    oldItem.copy(count = oldItem.count + 1)
                } else {
                    oldItem
                }
            }
        }
        val newFeedPost = feedPost.copy(statistics = newStatistics)
        val newPosts = oldPosts.apply {
            replaceAll {
                if (it.id == newFeedPost.id) {
                    newFeedPost
                } else {
                    it
                }
            }
        }
        _screenState.value = HomeScreenState.FeedPostsState(newPosts)
    }

    fun deleteFeedPost(feedPost: FeedPost) {
        val currentState = screenState.value
        if (currentState !is HomeScreenState.FeedPostsState) return

        val oldPosts = currentState.posts.toMutableList()
        oldPosts.remove(feedPost)
        _screenState.value = HomeScreenState.FeedPostsState(oldPosts)
    }
}