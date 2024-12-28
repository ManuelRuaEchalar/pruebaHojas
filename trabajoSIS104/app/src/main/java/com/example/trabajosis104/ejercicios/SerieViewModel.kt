package com.example.trabajosis104.ejercicios

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SerieViewModel : ViewModel() {

    private val serieModel = SerieModel()

    private val _resultado = MutableLiveData<String>()
    val resultado: LiveData<String> get() = _resultado

    // Método para realizar el cálculo de la serie
    fun calcularSerie(kMax: Int) {
        val resultadoSerie = serieModel.calcularSerie(kMax)
        _resultado.value = "Resultado: $resultadoSerie"
    }
}