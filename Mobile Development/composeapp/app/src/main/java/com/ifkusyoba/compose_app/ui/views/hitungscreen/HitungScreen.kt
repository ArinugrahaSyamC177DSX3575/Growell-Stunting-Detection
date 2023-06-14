package com.ifkusyoba.compose_app.ui.views.hitungscreen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ifkusyoba.compose_app.di.Injection
import com.ifkusyoba.compose_app.ui.components.BottomNavigationBar
import com.ifkusyoba.compose_app.ui.components.EditTextCustom
import com.ifkusyoba.compose_app.ui.components.GenderDropdownCustom
import com.ifkusyoba.compose_app.ui.components.HitungButtonCustom
import com.ifkusyoba.compose_app.ui.theme.ComposeAppTheme
import com.ifkusyoba.compose_app.viewmodel.FieldType
import com.ifkusyoba.compose_app.viewmodel.HitungViewModel
import com.ifkusyoba.compose_app.viewmodel.ViewModelFactory
import com.ifkusyoba.compose_app.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HitungScreen(
    modifier: Modifier = Modifier,
    viewModel: HitungViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository())),
    navController: NavController
) {
    val context = LocalContext.current
    Scaffold(
        modifier = modifier,
        bottomBar = {
            BottomNavigationBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { },
                navController = navController
            )
        }
    ) { innerPadding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.surface)
        ) {
            Column(
                modifier = modifier
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(stringResource(id = R.string.hitung_welcome), modifier = Modifier.align(Alignment.CenterHorizontally))
                Text(
                    stringResource(id = R.string.hitung_welcome_subtitle),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = stringResource(id = R.string.hitung_name), style = MaterialTheme.typography.bodyLarge)
                EditTextCustom(
                    text = viewModel.nameValue.value,
                    label = stringResource(id = R.string.hitung_name_hint),
                    onTextChange = { viewModel.onTextChange(it, FieldType.NAME) }
                )
                Text(text = stringResource(id = R.string.hitung_jenis_kelamin), style = MaterialTheme.typography.bodyLarge)
                GenderDropdownCustom { selectedGender ->
                    viewModel.onTextChange(selectedGender, FieldType.GENDER)
                }
                Text(text = stringResource(id = R.string.hitung_usia), style = MaterialTheme.typography.bodyLarge)
                EditTextCustom(
                    text = viewModel.ageValue.value,
                    label = stringResource(id = R.string.hitung_usia_hint),
                    onTextChange = { viewModel.onTextChange(it, FieldType.AGE) },
                    keyboardType = KeyboardType.Number
                )
                Text(text = stringResource(id = R.string.hitung_tinggi_badan), style = MaterialTheme.typography.bodyLarge)
                EditTextCustom(
                    text = viewModel.heightValue.value,
                    label = stringResource(id = R.string.hitung_tinggi_badan_hint),
                    onTextChange = { viewModel.onTextChange(it, FieldType.HEIGHT) },
                    keyboardType = KeyboardType.Number
                )
                Text(text = stringResource(id = R.string.hitung_berat_badan), style = MaterialTheme.typography.bodyLarge)
                EditTextCustom(
                    text = viewModel.weightValue.value,
                    label = stringResource(id = R.string.hitung_berat_badan_hint),
                    onTextChange = { viewModel.onTextChange(it, FieldType.WEIGHT) },
                    keyboardType = KeyboardType.Number
                )
                Spacer(modifier = Modifier.height(16.dp))
                HitungButtonCustom(text = stringResource(id = R.string.hitung_submit), onClick = {
                    if (viewModel.nameValue.value.isEmpty() || viewModel.ageValue.value.isEmpty() || viewModel.heightValue.value.isEmpty() || viewModel.weightValue.value.isEmpty()) {
                        Toast.makeText(
                            context,
                            R.string.hitung_validation_datadiri,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        return@HitungButtonCustom
                    } else if (!viewModel.ageValue.value.isDigitsOnly() || !viewModel.heightValue.value.isDigitsOnly() || !viewModel.weightValue.value.isDigitsOnly()) {
                        Toast.makeText(
                            context,
                            R.string.hitung_validation_angka,
                            Toast.LENGTH_SHORT
                        ).show()
                        return@HitungButtonCustom
                    } else {
                        viewModel.calculateBMI(
                            viewModel.heightValue.value.toInt(),
                            viewModel.weightValue.value.toInt()
                        )
                        viewModel.getUserData(
                            viewModel.nameValue.value,
                            viewModel.genderValue.value,
                            viewModel.ageValue.value.toInt(),
                            viewModel.heightValue.value.toInt(),
                            viewModel.weightValue.value.toInt()
                        )
                        Toast.makeText(context, R.string.hitung_validation_sended, Toast.LENGTH_SHORT)
                            .show()
                    }
                })
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HitungScreenPreview() {
    ComposeAppTheme {
//        HitungScreen()
    }
}