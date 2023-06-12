package com.ifkusyoba.compose_app.ui.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ifkusyoba.compose_app.ui.theme.ComposeAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenderDropdownCustom() {
    val genderOptions = listOf("Laki-laki", "Perempuan")
    var selectedGender by remember { mutableStateOf(genderOptions[0]) }
    var expanded by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = true }
            .clip(RoundedCornerShape(24.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Text(
            text = selectedGender,
            modifier = Modifier.padding(16.dp),
            fontSize = 16.sp
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            genderOptions.forEach { gender ->
                DropdownMenuItem(
                    onClick = {
                        selectedGender = gender
                        expanded = false
                    },
                    text = {
                        Text(text = gender)
                    }
                )
            }
        }
    }
}


@Preview
@Composable
fun GenderDropdownCustomPreview() {
    ComposeAppTheme {
        GenderDropdownCustom()
    }
}