package com.bionickhand.vknewsclient.navigation

import android.net.Uri
import com.bionickhand.vknewsclient.domain.FeedPost
import com.google.gson.Gson

sealed class Screen(
    val route: String
){
    data object HomeScreen: Screen(ROUTE_HOME)

    data object CommentsScreen: Screen(ROUTE_COMMENTS){
        private const val ROUTE_FOR_ARGS = "comments"

        fun getRouteWithArgs(feedPost: FeedPost): String{
            val feedPostJson = Gson().toJson(feedPost)
            return "$ROUTE_FOR_ARGS/${Uri.encode(feedPostJson)}"
        }
    }

    data object FeedScreen: Screen(ROUTE_FEED_POST)
    data object FavoriteScreen: Screen(ROUTE_FAVORITE)
    data object ProfileScreen: Screen(ROUTE_PROFILE)

    companion object{
        const val KEY_FEED_POST = "feed_post"

        const val ROUTE_HOME = "home"
        const val ROUTE_COMMENTS = "comments/{$KEY_FEED_POST}"
        const val ROUTE_FEED_POST = "feed_post"
        const val ROUTE_FAVORITE = "favorite"
        const val ROUTE_PROFILE = "profile"
    }
}