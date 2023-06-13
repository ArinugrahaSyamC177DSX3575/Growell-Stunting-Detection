package com.ifkusyoba.compose_app.ui.views.resultscreen

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.ifkusyoba.compose_app.R
import com.ifkusyoba.compose_app.ui.theme.ComposeAppTheme

@Composable
fun HealthyConfirmationScreen(){
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.happy_face))
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(composition = composition, modifier = Modifier.size(400.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun HealthyConfirmationScreenPreview(){
    ComposeAppTheme {
        HealthyConfirmationScreen()
    }
}