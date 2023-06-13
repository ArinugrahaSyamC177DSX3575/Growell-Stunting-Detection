package com.ifkusyoba.compose_app.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.ifkusyoba.compose_app.R
import com.ifkusyoba.compose_app.navigation.Screen
import com.ifkusyoba.compose_app.ui.theme.ComposeAppTheme

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    navController: NavController
){
    BottomAppBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        IconButton(onClick = {
            navController.navigate(Screen.Dashboard.route)
        }) {
            Icon(imageVector = Icons.Rounded.Home, contentDescription = "Home")
        }
        IconButton(onClick = {
            navController.navigate(Screen.HitungScreen.route)
        }) {
            Icon(painter = painterResource(id = R.drawable.ic_baseline_calculate_24), contentDescription = "Calculate")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomAppbarPreview(){
    ComposeAppTheme {
        /*BottomNavigationBar(
        )*/
    }
}