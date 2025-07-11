package com.deepakjetpackcompose.quickaid.domain.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.deepakjetpackcompose.quickaid.presentation.screen.AppScreen
import com.deepakjetpackcompose.quickaid.presentation.screen.GetStartedScreen
import com.deepakjetpackcompose.quickaid.presentation.screen.LoginScreen
import com.deepakjetpackcompose.quickaid.presentation.screen.SignUpScreen

@Composable
fun NavApp(modifier: Modifier = Modifier) {

    val navController= rememberNavController()
    NavHost(navController=navController, startDestination = NavigationHelper.OnBoardingScreen){
        composable <NavigationHelper.OnBoardingScreen>{ GetStartedScreen(navController = navController) }
        composable <NavigationHelper.LoginScreen>{ LoginScreen(navController = navController) }
        composable <NavigationHelper.SignUpScreen>{ SignUpScreen(navController = navController) }
        composable <NavigationHelper.AppScreen>{ AppScreen(navController = navController) }
    }

}