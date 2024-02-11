package com.axat.newzo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.axat.newzo.ui.screens.home.DashboardScreen
import com.axat.newzo.ui.screens.splash.SplashScreen


@Composable
fun NavigationGraph() {

    val navController = rememberNavController()


    NavHost(
        navController = navController,
        startDestination = NavigationRoute.SPLASH_SCREEN,
    ) {

        composable(NavigationRoute.SPLASH_SCREEN) {
            SplashScreen(navController = navController)
        }
        composable(NavigationRoute.DASHBOARD_SCREEN) {
            DashboardScreen()
        }

    }

}