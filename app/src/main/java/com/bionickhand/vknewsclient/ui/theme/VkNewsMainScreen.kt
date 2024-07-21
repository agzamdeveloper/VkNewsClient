package com.bionickhand.vknewsclient.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.bionickhand.vknewsclient.domain.FeedPost
import com.bionickhand.vknewsclient.navigation.AppNavigation
import com.bionickhand.vknewsclient.navigation.rememberNavigationState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navigationState = rememberNavigationState()
    val commentsToPost: MutableState<FeedPost?> = remember {
        mutableStateOf(null)
    }

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()
                val items = listOf(
                    NavigationItem.Home,
                    NavigationItem.Favourites,
                    NavigationItem.Profiles
                )
                items.forEach { item ->
                    val selected = navBackStackEntry?.destination?.hierarchy?.any {
                        it.route == item.screen.route
                    } ?: false
                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            if (!selected) {
                                navigationState.navigateTo(item.screen.route)
                            }
                        },
                        icon = {
                            Icon(item.icon, contentDescription = null)
                        },
                        label = {
                            Text(text = stringResource(id = item.titleResId))
                        },
                        colors = NavigationBarItemColors(
                            selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                            selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                            selectedIndicatorColor = MaterialTheme.colorScheme.onSecondary,
                            unselectedIconColor = MaterialTheme.colorScheme.onSecondary,
                            unselectedTextColor = MaterialTheme.colorScheme.onSecondary,
                            disabledIconColor = MaterialTheme.colorScheme.onSecondary,
                            disabledTextColor = MaterialTheme.colorScheme.onSecondary
                        )
                    )
                }
            }
        }
    ) {
        AppNavigation(
            navController = navigationState.navHostController,
            feedScreenContent = {
                HomeScreen(
                    onCommentsClickListener = {
                        commentsToPost.value = it
                        navigationState.navigateToComments()
                    }
                )
            },
            commentsScreenContent = {
                CommentScreen(
                    onBackPressed = {
                        navigationState.navHostController.popBackStack()
                    },
                    feedPost = commentsToPost.value!!
                )
            },
            favoriteScreenContent = {
                ClickableText(text = "Favorites")
            },
            profileScreenContent = {
                ClickableText(text = "Profiles")
            }
        )
    }
}

@Composable
fun ClickableText(
    text: String
) {
    var count by rememberSaveable {
        mutableIntStateOf(0)
    }
    Text(
        modifier = Modifier.clickable {
            count++
        },
        text = "$text count: $count"
    )
}