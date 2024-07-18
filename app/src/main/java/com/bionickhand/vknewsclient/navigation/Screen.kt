package com.bionickhand.vknewsclient.navigation

sealed class Screen(
    val route: String
){
    object FeedScreen: Screen(SCREEN_FEED_POST)
    object FavoriteScreen: Screen(SCREEN_FAVORITE)
    object ProfileScreen: Screen(SCREEN_PROFILE)

    companion object{
        const val SCREEN_FEED_POST = "feed_post"
        const val SCREEN_FAVORITE = "favorite"
        const val SCREEN_PROFILE = "profile"
    }
}