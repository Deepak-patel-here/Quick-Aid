package com.deepakjetpackcompose.quickaid.domain.navigation

import kotlinx.serialization.Serializable

sealed class NavigationHelper {

    @Serializable
    object OnBoardingScreen: NavigationHelper()

    @Serializable
    object LoginScreen: NavigationHelper()

    @Serializable
    object SignUpScreen: NavigationHelper()

    @Serializable
    object AppScreen: NavigationHelper()
}