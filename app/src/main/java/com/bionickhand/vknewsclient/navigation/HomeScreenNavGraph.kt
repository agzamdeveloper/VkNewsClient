package com.bionickhand.vknewsclient.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.homeScreenNavGraph(
    feedScreenContent: @Composable ()->Unit,
    commentsScreenContent: @Composable ()->Unit
){
    navigation(
        startDestination = Screen.FeedScreen.route,
        route = Screen.ROUTE_HOME
    ){
        composable(Screen.FeedScreen.route) {
            feedScreenContent()
        }
        composable(Screen.CommentsScreen.route) {
            commentsScreenContent()
        }
    }
}