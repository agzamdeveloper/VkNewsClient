package com.bionickhand.vknewsclient.presentation.main

sealed class AuthState {
    data object Initial: AuthState()
    data object Authorized: AuthState()
    data object NotAuthorized: AuthState()
}