package com.axat.newzo.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.axat.newzo.ui.screens.detail.NewsDetailScreen
import com.axat.newzo.ui.screens.home.DashboardScreen
import com.axat.newzo.ui.screens.splash.SplashScreen
import com.axat.newzo.utility.SharedViewModel


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationGraph() {

    val navController = rememberNavController()
    val sharedViewModel: SharedViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = NavigationRoute.SPLASH_SCREEN,
    ) {

        composable(NavigationRoute.SPLASH_SCREEN) {
            SplashScreen(navController = navController)
        }
        composable(NavigationRoute.DASHBOARD_SCREEN) {
            DashboardScreen(navController = navController, sharedViewModel = sharedViewModel)
        }
        composable(NavigationRoute.NEWS_DETAIL_SCREEN) {
            NewsDetailScreen(navController = navController, sharedViewModel = sharedViewModel)
        }

    }

}