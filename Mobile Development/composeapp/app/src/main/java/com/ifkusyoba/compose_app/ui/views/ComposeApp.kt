package com.ifkusyoba.compose_app.ui.views

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.ifkusyoba.compose_app.ui.theme.ComposeAppTheme
import com.ifkusyoba.compose_app.ui.components.EditTextCustom
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.ifkusyoba.compose_app.viewmodel.MainViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ifkusyoba.compose_app.ui.components.GenderDropdownCustom
import com.ifkusyoba.compose_app.ui.components.HitungButtonCustom
import com.ifkusyoba.compose_app.viewmodel.FieldType
import com.ifkusyoba.compose_app.viewmodel.ViewModelFactory

@Composable
fun ComposeApp(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel(factory = ViewModelFactory()),
) {
    val context = LocalContext.current
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            modifier = modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text("Selamat Datang", modifier = Modifier.align(Alignment.CenterHorizontally))
            Text(
                "Silakan masukkan data diri Anda!",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Nama", style = MaterialTheme.typography.bodyLarge)
            EditTextCustom(
                text = viewModel.nameValue.value,
                label = "Masukkan nama Anda!",
                onTextChange = { viewModel.onTextChange(it, FieldType.NAME) }
            )
            Text(text = "Jenis Kelamin", style = MaterialTheme.typography.bodyLarge)
            GenderDropdownCustom()
            Text(text = "Usia", style = MaterialTheme.typography.bodyLarge)
            EditTextCustom(
                text = viewModel.ageValue.value,
                label = "Masukkan usia Anda!",
                onTextChange = { viewModel.onTextChange(it, FieldType.AGE) },
                keyboardType = KeyboardType.Number
            )
            Text(text = "Tinggi Badan", style = MaterialTheme.typography.bodyLarge)
            EditTextCustom(
                text = viewModel.heightValue.value,
                label = "Masukkan tinggi badan Anda! (cm)",
                onTextChange = { viewModel.onTextChange(it, FieldType.HEIGHT) },
                keyboardType = KeyboardType.Number
            )
            Text(text = "Berat Badan", style = MaterialTheme.typography.bodyLarge)
            EditTextCustom(
                text = viewModel.weightValue.value,
                label = "Masukkan berat badan Anda! (kg)",
                onTextChange = { viewModel.onTextChange(it, FieldType.WEIGHT) },
                keyboardType = KeyboardType.Number
            )
            Spacer(modifier = Modifier.height(16.dp))
            HitungButtonCustom(text = "Hitung", onClick = {
                if (viewModel.nameValue.value.isEmpty() || viewModel.ageValue.value.isEmpty() || viewModel.heightValue.value.isEmpty() || viewModel.weightValue.value.isEmpty()) {
                    Toast.makeText(context, "Mohon lengkapi data diri Anda!", Toast.LENGTH_SHORT)
                        .show()
                    return@HitungButtonCustom
                } else {
                    viewModel.calculateBMI(
                        viewModel.heightValue.value.toInt(),
                        viewModel.weightValue.value.toInt()
                    )
                }
            })
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ComposeAppPreview() {
    ComposeAppTheme {
        ComposeApp()
    }
}