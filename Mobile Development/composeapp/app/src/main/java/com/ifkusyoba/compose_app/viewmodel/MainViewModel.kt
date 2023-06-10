package com.ifkusyoba.compose_app.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ifkusyoba.compose_app.data.model.Faq
import com.ifkusyoba.compose_app.data.repository.Repository
import com.ifkusyoba.compose_app.ui.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Faq>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: MutableStateFlow<UiState<List<Faq>>>
        get() = _uiState

    fun getAllFaq() {
        viewModelScope.launch {
            repository.getFaqList()
                .catch { errorMsg ->
                    _uiState.value = UiState.Error(errorMsg.message.toString())
                }
                .collect { faq ->
                    _uiState.value = UiState.Success(faq)
                }
        }
    }
}

