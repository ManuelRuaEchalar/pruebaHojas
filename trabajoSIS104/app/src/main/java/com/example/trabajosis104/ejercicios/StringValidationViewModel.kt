// StringValidationViewModel.kt
package com.example.trabajosis104.ejercicios

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StringValidationViewModel : ViewModel() {
    private val model = StringValidationModel()

    private val _validationResult = MutableLiveData<String>()
    val validationResult: LiveData<String> = _validationResult

    private val _formattedString = MutableLiveData<String>()
    val formattedString: LiveData<String> = _formattedString

    fun validateInput(input: String) {
        val (result, formatted) = model.validateString(input)
        _validationResult.value = result
        _formattedString.value = formatted
    }
}
