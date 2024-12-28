package com.example.trabajosis104.ejercicios

import kotlin.math.pow

class GraficoModel {

    // Función para calcular el cuadrado
    fun fx(x: Float): Double {
        return x.pow(2).toDouble()
    }

    // Función para calcular x * sen(x)
    fun fxsin(x: Float): Double {
        return x * kotlin.math.sin(x.toDouble())
    }

    // Función para calcular la altura del triángulo equilátero
    fun calcularAltura(lado: Float): Double {
        return kotlin.math.sqrt(lado.pow(2) - (lado / 2).pow(2)).toDouble()
    }

    // Función para calcular la altura intermedia
    fun calcularAlturaIntermedia(lado: Float): Double {
        return kotlin.math.sqrt((lado / 2).pow(2) - (lado / 4).pow(2)).toDouble()
    }
}