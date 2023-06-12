package com.ifkusyoba.compose_app.data.model

import androidx.compose.ui.graphics.vector.ImageVector
import com.ifkusyoba.compose_app.navigation.Screen

data class BottomAppBarItem(
    val title: String,
    val icon: ImageVector,
    val screen: Screen
)