package com.example.trabajosis104.ejercicios

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DivisionViewModel : ViewModel() {

    private val divisionModel = DivisionModel()

    private val _resultado = MutableLiveData<String>()
    val resultado: LiveData<String> get() = _resultado

    fun dividir(numerador: Int, denominador: Int, decimales: Int) {
        try {
            val resultadoDivision = divisionModel.dividirPorRestasSucesivas(numerador, denominador, decimales)
            _resultado.value = resultadoDivision
        } catch (e: IllegalArgumentException) {
            _resultado.value = e.message
        }
    }
}