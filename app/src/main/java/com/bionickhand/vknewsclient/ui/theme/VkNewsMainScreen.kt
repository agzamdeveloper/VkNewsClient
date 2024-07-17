package com.bionickhand.vknewsclient.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bionickhand.vknewsclient.MainViewModel
import com.bionickhand.vknewsclient.domain.FeedPost

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    viewModel: MainViewModel
) {
    Scaffold(
        bottomBar = {
            NavigationBar {
                val selectedItemPosition = remember {
                    mutableIntStateOf(0)
                }
                val items = listOf(
                    NavigationItem.Home,
                    NavigationItem.Favourites,
                    NavigationItem.Profiles
                )
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItemPosition.intValue == index,
                        onClick = { selectedItemPosition.intValue = index },
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
        val feedPost = viewModel.feedPost.observeAsState(FeedPost())
        PostCard(
            modifier = Modifier.padding(8.dp),
            feedPost = feedPost.value,
            onViewsClickListener = viewModel::changeStatisticItem,
            onSharesClickListener = viewModel::changeStatisticItem,
            onCommentsClickListener = viewModel::changeStatisticItem,
            onLikesClickListener = {
                viewModel.changeStatisticItem(it)
            }
        )
    }
}