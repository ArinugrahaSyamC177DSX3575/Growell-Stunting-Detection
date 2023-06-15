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

    fun onTextChange(value: String, fieldType: FieldType) {
        when (fieldType) {
            FieldType.NAME -> _nameValue.value = value
            FieldType.GENDER -> _genderValue.value = value
            FieldType.AGE -> _ageValue.value = value
            FieldType.HEIGHT -> _heightValue.value = value
            FieldType.WEIGHT -> _weightValue.value = value
        }
    }

    fun calculateBMI(height: Int, weight: Int) {
        val heightInMeters = height / 100.0
        val bmi = weight / (heightInMeters * heightInMeters)
        val result = when {
            bmi < 18 -> "Kekurangan berat badan"
            bmi < 25 -> "Berat badan ideal"
            bmi < 30 -> "Kelebihan berat badan"
            else -> "Obesitas"
        }
        Log.d("BMI", "calculateBMI: $height")
        Log.d("BMI", "calculateBMI: $weight")
        Log.d("BMI", "calculateBMI: $bmi")
        Log.d("BMI", "calculateBMI: $result")
    }

    fun getUserData(name: String, gender: String, age: Int, height: Int, weight: Int) {
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

    fun sendData(context: Context, name: String, gender: String, age: Int, height: Int, weight: Int) {
        val selectedGender = if (gender.isEmpty()) "L" else gender
        viewModelScope.launch {
            val apiService = ApiConfig.startApiService()
            val response = apiService.addStunting(name, selectedGender, age, height, weight)
            if (response.message == "success") {
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