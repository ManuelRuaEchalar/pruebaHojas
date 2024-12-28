package com.example.trabajosis104.ejercicios

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GraficoViewModel : ViewModel() {

    private val graficoModel = GraficoModel()

    // LiveData para almacenar los resultados de los cálculos
    private val _resultadoFx = MutableLiveData<Double>()
    val resultadoFx: LiveData<Double> get() = _resultadoFx

    private val _resultadoFxSin = MutableLiveData<Double>()
    val resultadoFxSin: LiveData<Double> get() = _resultadoFxSin

    private val _alturaTriangulo = MutableLiveData<Double>()
    val alturaTriangulo: LiveData<Double> get() = _alturaTriangulo

    private val _alturaIntermedia = MutableLiveData<Double>()
    val alturaIntermedia: LiveData<Double> get() = _alturaIntermedia

    // Función para calcular fx
    fun calcularFx(x: Float) {
        _resultadoFx.value = graficoModel.fx(x)
    }

    // Función para calcular fxsin
    fun calcularFxSin(x: Float) {
        _resultadoFxSin.value = graficoModel.fxsin(x)
    }

    // Función para calcular altura del triángulo
    fun calcularAlturaTriangulo(lado: Float) {
        _alturaTriangulo.value = graficoModel.calcularAltura(lado)
    }

    // Función para calcular altura intermedia
    fun calcularAlturaIntermedia(lado: Float) {
        _alturaIntermedia.value = graficoModel.calcularAlturaIntermedia(lado)
    }
}
