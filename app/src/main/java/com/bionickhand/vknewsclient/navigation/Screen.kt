package com.bionickhand.vknewsclient.navigation

sealed class Screen(
    val route: String
){
    data object HomeScreen: Screen(ROUTE_HOME)
    data object CommentsScreen: Screen(ROUTE_COMMENTS)
    data object FeedScreen: Screen(ROUTE_FEED_POST)
    data object FavoriteScreen: Screen(ROUTE_FAVORITE)
    data object ProfileScreen: Screen(ROUTE_PROFILE)

    companion object{
        const val ROUTE_HOME = "home"
        const val ROUTE_COMMENTS = "comments"
        const val ROUTE_FEED_POST = "feed_post"
        const val ROUTE_FAVORITE = "favorite"
        const val ROUTE_PROFILE = "profile"
    }
}