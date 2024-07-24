package com.bionickhand.vknewsclient.ui.theme

sealed class AuthState {
    data object Initial: AuthState()
    data object Authorized: AuthState()
    data object NotAuthorized: AuthState()
}