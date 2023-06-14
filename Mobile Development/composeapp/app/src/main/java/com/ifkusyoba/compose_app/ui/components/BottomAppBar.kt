package com.ifkusyoba.compose_app.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ifkusyoba.compose_app.R
import com.ifkusyoba.compose_app.navigation.Screen
import com.ifkusyoba.compose_app.ui.theme.ComposeAppTheme

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    BottomAppBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.background
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(
                onClick = {
                    navController.navigate(Screen.Dashboard.route)
                },
                modifier = Modifier.padding(horizontal = 16.dp).size(128.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Home,
                    contentDescription = "Home",
                    tint = if (navController.currentDestination?.route == Screen.Dashboard.route) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.background
                        LocalContentColor.current.copy(alpha = 0.6f)
                    }
                )
            }
            IconButton(
                onClick = {
                    navController.navigate(Screen.HitungScreen.route)
                },
                modifier = Modifier.padding(horizontal = 16.dp).size(128.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_calculate_24),
                    contentDescription = "Calculate",
                    tint = if (navController.currentDestination?.route == Screen.HitungScreen.route) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.background
                        LocalContentColor.current.copy(alpha = 0.6f)
                    }
                )
            }
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