package com.ifkusyoba.compose_app.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ifkusyoba.compose_app.data.remote.ApiConfig
import kotlinx.coroutines.launch
import retrofit2.Response as responseCode

class HitungViewModel : ViewModel() {
    private val _nameValue = mutableStateOf("")
    val nameValue: State<String> = _nameValue

    private val _genderValue = mutableStateOf("")
    val genderValue: State<String> = _genderValue

    private val _heightValue = mutableStateOf("")
    val heightValue: State<String> = _heightValue

    private val _ageValue = mutableStateOf("")
    val ageValue: State<String> = _ageValue

    private val _weightValue = mutableStateOf("")
    val weightValue: State<String> = _weightValue

    private val _stuntingStatus = mutableStateOf<StuntingStatus?>(null)
    val stuntingStatus: State<StuntingStatus?> = _stuntingStatus

    private val _loadingState = mutableStateOf(false)
    var loadingState: State<Boolean> = _loadingState

    fun onTextChange(value: String, fieldType: FieldType) {
        when (fieldType) {
            FieldType.NAME -> _nameValue.value = value
            FieldType.GENDER -> _genderValue.value = value
            FieldType.AGE -> _ageValue.value = value
            FieldType.HEIGHT -> _heightValue.value = value
            FieldType.WEIGHT -> _weightValue.value = value
        }
    }

    fun getUserData(name: String, gender: String, age: Double, height: Double, weight: Double) {
        _nameValue.value = name
        _genderValue.value = gender
        _ageValue.value = age.toString()
        _heightValue.value = height.toString()
        _weightValue.value = weight.toString()
        Log.d("User Data", "getUserData: $name")
        Log.d("User Data", "getUserData: $gender")
        Log.d("User Data", "getUserData: $age")
        Log.d("User Data", "getUserData: $height")
        Log.d("User Data", "getUserData: $weight")
    }

    fun sendData(
        context: Context,
        name: String,
        gender: String,
        age: Double,
        height: Double,
        weight: Double
    ) {
        val selectedGender = if (gender.isEmpty()) "L" else gender
        viewModelScope.launch {
            _loadingState.value = true
            val apiService = ApiConfig.startApiService()
            try {
                val response = apiService.addStunting(name, selectedGender, age, height, weight)
                val stuntingResponse = response.uploadResult.status
                if (!response.error && response.message == "success") {
                    if (stuntingResponse == "Tidak Stunting") {
                        _stuntingStatus.value = StuntingStatus.TIDAK_STUNTING
                    } else {
                        _stuntingStatus.value = StuntingStatus.STUNTING
                    }
                    Log.d("Send Data", "sendData: ${response.message}")
                    Toast.makeText(
                        context,
                        "Data berhasil dikirim",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Log.d("Send Data", "sendData: ${response.message}")
                    Toast.makeText(
                        context,
                        "Data gagal dikirim",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } catch (e: Exception) {
                Log.e("Send Data", "sendData: ${e.message}", e)
                Toast.makeText(
                    context,
                    "Terjadi kesalahan saat mengirim data",
                    Toast.LENGTH_SHORT
                ).show()
            } finally {
                _loadingState.value = false
            }
        }
    }
}

enum class FieldType {
    NAME,
    GENDER,
    HEIGHT,
    AGE,
    WEIGHT
}

enum class StuntingStatus {
    TIDAK_STUNTING,
    STUNTING
}