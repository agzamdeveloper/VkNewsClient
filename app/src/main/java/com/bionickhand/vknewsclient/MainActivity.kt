package com.bionickhand.vknewsclient

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.compose.runtime.SideEffect
import com.bionickhand.vknewsclient.ui.theme.ActivityResultTest
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
                val launcher = rememberLauncherForActivityResult(
                    contract = VK.getVKAuthActivityResultContract()
                ) {
                    when (it) {
                        is VKAuthenticationResult.Success -> {
                            Log.d("MainActivity", "success auth")
                        }
                        is VKAuthenticationResult.Failed -> {
                            Log.d("MainActivity", "failed auth")
                        }
                    }
                }
                SideEffect {
                    launcher.launch(listOf(VKScope.WALL))
                }

                MainScreen()
            }
        }
    }
}
