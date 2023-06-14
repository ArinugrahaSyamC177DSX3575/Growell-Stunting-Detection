package com.ifkusyoba.compose_app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ifkusyoba.compose_app.ui.views.ComposeApp
import com.ifkusyoba.compose_app.ui.views.hitungscreen.HitungScreen
import com.ifkusyoba.compose_app.ui.views.resultscreen.HealthyConfirmationScreen
import com.ifkusyoba.compose_app.ui.views.resultscreen.StuntingConfirmationScreen
import com.ifkusyoba.compose_app.ui.views.splashscreen.SplashScreen


@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.SplashScreen.route){
        composable(route = Screen.SplashScreen.route){
            SplashScreen(navController = navController)
        }
        composable(route = Screen.Dashboard.route){
            ComposeApp(
                navController = navController
            )
        }
        composable(route = Screen.HitungScreen.route){
            HitungScreen(
                navController = navController
            )
        }
        composable(route = Screen.HealthyScreen.route){
            HealthyConfirmationScreen(
                navController = navController
            )
        }
        composable(route = Screen.StuntingScreen.route){
            StuntingConfirmationScreen(
                navController = navController
            )
        }
    }
}