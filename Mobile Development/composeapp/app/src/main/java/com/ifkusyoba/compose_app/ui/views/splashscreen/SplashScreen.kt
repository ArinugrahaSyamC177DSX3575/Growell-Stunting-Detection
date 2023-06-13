package com.ifkusyoba.compose_app.ui.views.splashscreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ifkusyoba.compose_app.R
import com.ifkusyoba.compose_app.navigation.Screen
import com.ifkusyoba.compose_app.ui.theme.ComposeAppTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController
) {
    val splashScreenVisible = remember { mutableStateOf(true) }
    LaunchedEffect(key1 = true) {
        delay(3000L)
        splashScreenVisible.value = false
        navController.navigate(Screen.Dashboard.route)
    }
    AnimatedVisibility(visible = splashScreenVisible.value, enter = fadeIn(), exit = fadeOut()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primary)
        ) {
            Image(
                painter = painterResource(id = R.drawable.growell),
                contentDescription = "Logo",
                modifier = Modifier
                    .align(alignment = Alignment.Center)
                    .size(200.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    ComposeAppTheme {
//        SplashScreen()
    }
}