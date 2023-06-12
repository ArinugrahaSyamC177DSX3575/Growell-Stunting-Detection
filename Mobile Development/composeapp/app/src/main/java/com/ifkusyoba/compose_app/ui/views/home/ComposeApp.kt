package com.ifkusyoba.compose_app.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ifkusyoba.compose_app.data.model.Faq
import com.ifkusyoba.compose_app.di.Injection
import com.ifkusyoba.compose_app.ui.components.BottomNavigationBar
import com.ifkusyoba.compose_app.ui.components.ExpandableCardCustom
import com.ifkusyoba.compose_app.ui.state.UiState
import com.ifkusyoba.compose_app.ui.theme.ComposeAppTheme
import com.ifkusyoba.compose_app.viewmodel.MainViewModel
import com.ifkusyoba.compose_app.viewmodel.ViewModelFactory

// *Dashboard
@Composable
fun ComposeApp(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository())),
    navController: NavController
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { state ->
        when (state) {
            is UiState.Loading -> {
                viewModel.getAllFaq()
            }
            is UiState.Success -> {
                ComposeAppContent(
                    faq = state.data,
                    modifier = modifier,
                    navController = navController
                )
            }
            is UiState.Error -> {
                // *Nothiing
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComposeAppContent(
    faq: List<Faq>,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { },
                navController = navController
            )
        }
    ) {innerPadding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.surface)
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = modifier
                    .padding(16.dp),
            ) {
                items(faq) { data ->
                    ExpandableCardCustom(
                        title = data.question,
                        answer = data.answer,
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ComposeAppPreview() {
    ComposeAppTheme {
        /*ComposeApp(

        )*/
    }
}