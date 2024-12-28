package com.example.trabajosis104.ejercicios

class SerieModel {

    // Función para calcular el factorial
    private fun factorial(n: Int): Long {
        var fact: Long = 1
        for (i in 2..n) {
            fact *= i
        }
        return fact
    }

    // Método para calcular la serie de Ramanujan para aproximar 1/π
    fun calcularSerie(kMax: Int): Double {
        var resultado = 0.0
        val constante = (2 * Math.sqrt(2.0)) / 9801

        // Bucle para la sumatoria desde k=0 hasta kMax
        for (k in 0..kMax) {
            val numerador = factorial(4 * k) * (1103 + 26390 * k)
            val denominador = Math.pow(factorial(k).toDouble(), 4.0) * Math.pow(396.0, (4 * k).toDouble())
            resultado += numerador / denominador
        }

        // Fórmula completa de la serie
        resultado *= constante
        return 1 / resultado // Invertimos el resultado para obtener π
    }
}