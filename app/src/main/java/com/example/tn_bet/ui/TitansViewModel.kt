package com.example.tn_bet.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tn_bet.data.RetrofitClient
import com.example.tn_bet.data.Team
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class TitansUiState {
    object Loading : TitansUiState()
    data class Success(val team: Team) : TitansUiState()
    data class Error(val message: String) : TitansUiState()
}

class TitansViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<TitansUiState>(TitansUiState.Loading)
    val uiState: StateFlow<TitansUiState> = _uiState.asStateFlow()

    init {
        fetchTitansData()
    }

    fun fetchTitansData() {
        viewModelScope.launch {
            _uiState.value = TitansUiState.Loading
            try {
                val response = RetrofitClient.titansApi.getTeamInfo()
                _uiState.value = TitansUiState.Success(response.team)
            } catch (e: Exception) {
                _uiState.value = TitansUiState.Error(e.message ?: "Unknown Error")
            }
        }
    }
}
