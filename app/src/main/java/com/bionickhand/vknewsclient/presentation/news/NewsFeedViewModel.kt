package com.bionickhand.vknewsclient.presentation.news

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bionickhand.vknewsclient.data.repository.NewsFeedRepository
import com.bionickhand.vknewsclient.domain.FeedPost
import kotlinx.coroutines.launch

class NewsFeedViewModel(application: Application) : AndroidViewModel(application) {

    private val initialState = NewsFeedScreenState.Initial

    private val _screenState = MutableLiveData<NewsFeedScreenState>(initialState)
    val screenState: LiveData<NewsFeedScreenState> = _screenState

    private val repository = NewsFeedRepository(application)

    init {
        _screenState.value = NewsFeedScreenState.Loading
        loadRecommendations()
    }

    private fun loadRecommendations() {
        viewModelScope.launch {
            val feedPosts = repository.loadRecommendations()
            _screenState.value = NewsFeedScreenState.PostsState(posts = feedPosts)
        }
    }

    fun loadNextRecommendations() {
        _screenState.value = NewsFeedScreenState.PostsState(
            posts = repository.feedPosts,
            nextDataIsLoading = true
        )
        loadRecommendations()
    }

    fun changeLikeStatus(feedPost: FeedPost) {
        viewModelScope.launch {
            repository.changeLikeStatus(feedPost)
            _screenState.value = NewsFeedScreenState.PostsState(posts = repository.feedPosts)
        }
    }

    fun deleteFeedPost(feedPost: FeedPost) {
        viewModelScope.launch {
            repository.deletePost(feedPost)
            _screenState.value = NewsFeedScreenState.PostsState(repository.feedPosts)
        }
    }
}