package com.bionickhand.vknewsclient.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.bionickhand.vknewsclient.domain.FeedPost
import com.google.gson.Gson

fun NavGraphBuilder.homeScreenNavGraph(
    feedScreenContent: @Composable () -> Unit,
    commentsScreenContent: @Composable (FeedPost) -> Unit
){
    navigation(
        startDestination = Screen.FeedScreen.route,
        route = Screen.ROUTE_HOME
    ){
        composable(Screen.FeedScreen.route) {
            feedScreenContent()
        }
        composable(
            Screen.CommentsScreen.route
        ) {
            val feedPostJson = it.arguments?.getString(Screen.KEY_FEED_POST) ?: ""
            val feedPost = Gson().fromJson(feedPostJson, FeedPost::class.java)
            commentsScreenContent(feedPost)
        }
    }
}