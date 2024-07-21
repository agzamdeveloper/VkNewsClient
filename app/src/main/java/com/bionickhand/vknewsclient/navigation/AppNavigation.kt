package com.bionickhand.vknewsclient.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavigation(
    navController: NavHostController,
    feedScreenContent: @Composable () -> Unit,
    commentsScreenContent: @Composable ()->Unit,
    favoriteScreenContent: @Composable () -> Unit,
    profileScreenContent: @Composable () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        homeScreenNavGraph(
            feedScreenContent,
            commentsScreenContent
        )
        composable(Screen.FavoriteScreen.route) {
            favoriteScreenContent()
        }
        composable(Screen.ProfileScreen.route) {
            profileScreenContent()
        }
    }
}