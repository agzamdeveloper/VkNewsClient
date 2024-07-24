package com.bionickhand.vknewsclient.presentation.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.bionickhand.vknewsclient.R
import com.bionickhand.vknewsclient.navigation.Screen

sealed class NavigationItem(
    val screen: Screen,
    val titleResId: Int,
    val icon: ImageVector
) {
    data object Home : NavigationItem(
        screen = Screen.HomeScreen,
        titleResId = R.string.navigation_item_home,
        icon = Icons.Outlined.Home
    )

    data object Favourites : NavigationItem(
        screen = Screen.FavoriteScreen,
        titleResId = R.string.navigation_item_favourites,
        icon = Icons.Outlined.Favorite
    )

    data object Profiles : NavigationItem(
        screen = Screen.ProfileScreen,
        titleResId = R.string.navigation_item_profile,
        icon = Icons.Outlined.Person
    )
}