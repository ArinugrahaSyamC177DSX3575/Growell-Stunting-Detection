package com.ifkusyoba.compose_app.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ifkusyoba.compose_app.data.model.CardItem
import com.ifkusyoba.compose_app.data.model.Faq
import com.ifkusyoba.compose_app.data.repository.Repository
import com.ifkusyoba.compose_app.ui.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {
    private val _faqUiState: MutableStateFlow<UiState<List<Faq>>> =
        MutableStateFlow(UiState.Loading)
    val faqUiState: MutableStateFlow<UiState<List<Faq>>>
        get() = _faqUiState

    private val _cardItemUiState: MutableStateFlow<UiState<List<CardItem>>> =
        MutableStateFlow(UiState.Loading)
    val cardItemUiState: MutableStateFlow<UiState<List<CardItem>>>
        get() = _cardItemUiState

    fun getAllFaq() {
        viewModelScope.launch {
            repository.getFaqList()
                .catch { errorMsg ->
                    _faqUiState.value = UiState.Error(errorMsg.message.toString())
                }
                .collect { faq ->
                    _faqUiState.value = UiState.Success(faq)
                }
        }
    }
    fun getAllCardItem(){
        viewModelScope.launch {
            repository.getRecomendationList()
                .catch { errorMsg ->
                    _cardItemUiState.value = UiState.Error(errorMsg.message.toString())
                }
                .collect { cardItem ->
                    _cardItemUiState.value = UiState.Success(cardItem)
                }
        }
    }

}

