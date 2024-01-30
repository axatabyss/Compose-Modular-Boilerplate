package com.axat.newzo.ui.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.axat.newzo.ui.screens.SplashScreen


@Composable
fun NavigationGraph() {

    val nacController = rememberNavController()


    NavHost(
        navController = nacController,
        startDestination = NavigationRoute.SPLASH_SCREEN,
    ) {

        composable(NavigationRoute.SPLASH_SCREEN) {
            BackHandler(true) {}
            SplashScreen()
        }

    }

}