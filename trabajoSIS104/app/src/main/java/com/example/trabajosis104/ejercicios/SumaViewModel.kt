package com.example.trabajosis104.ejercicios

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SumaViewModel : ViewModel() {

    private val sumaModel = SumaModel()

    private val _resultado = MutableLiveData<String>()
    val resultado: LiveData<String> get() = _resultado

    // Método para realizar el cálculo de la suma recursiva
    fun calcularSuma(a: Int, b: Int) {
        val resultadoSuma = sumaModel.sumaRecursiva(a, b)
        _resultado.value = "Resultado: $resultadoSuma"
    }
}
