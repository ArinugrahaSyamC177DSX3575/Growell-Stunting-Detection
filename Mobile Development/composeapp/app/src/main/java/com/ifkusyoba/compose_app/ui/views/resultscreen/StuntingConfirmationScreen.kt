package com.ifkusyoba.compose_app.ui.views.resultscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.ifkusyoba.compose_app.ui.theme.ComposeAppTheme
import com.ifkusyoba.compose_app.R

@Composable
fun StuntingConfirmationScreen() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.sad_face))

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
fun StuntingConfirmationPagePreview(){
    ComposeAppTheme {
        StuntingConfirmationScreen()
    }
}