package com.ifkusyoba.compose_app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ifkusyoba.compose_app.ui.views.ComposeApp
import com.ifkusyoba.compose_app.ui.views.hitungscreen.HitungScreen
import com.ifkusyoba.compose_app.ui.views.splashscreen.SplashScreen


@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.SplashScreen.route){
        composable(route = Screen.SplashScreen.route){
            SplashScreen(navController = navController)
        }
        composable(route = Screen.Dashboard.route){
            ComposeApp()
        }
        composable(route = Screen.HitungScreen.route){
            HitungScreen()
        }
    }
}