package com.bionickhand.vknewsclient

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bionickhand.vknewsclient.ui.theme.ActivityResultTest
import com.bionickhand.vknewsclient.ui.theme.AuthState
import com.bionickhand.vknewsclient.ui.theme.LoginScreen
import com.bionickhand.vknewsclient.ui.theme.MainScreen
import com.bionickhand.vknewsclient.ui.theme.VkNewsClientTheme
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAuthenticationResult
import com.vk.api.sdk.auth.VKScope

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VkNewsClientTheme {
                val viewModel: MainViewModel = viewModel()
                val authState = viewModel.authState.observeAsState(AuthState.Initial)

                val launcher = rememberLauncherForActivityResult(
                    contract = VK.getVKAuthActivityResultContract()
                ) {
                    viewModel.performAuthResult(it)
                }

                when(authState.value){
                    is AuthState.Authorized -> {
                        MainScreen()
                    }
                    is AuthState.NotAuthorized -> {
                        LoginScreen {
                            launcher.launch(listOf(VKScope.WALL))
                        }
                    }
                    else -> {}
                }
            }
        }
    }
}
