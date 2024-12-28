package com.example.trabajosis104.ejercicios

import kotlin.math.sqrt

class Ecuacion(private val a: Double, private val b: Double, private val c: Double) {

    // Método para calcular el discriminante
    private fun discriminante(): Double {
        return (b * b) - (4 * a * c)
    }

    // Método para calcular las raíces
    fun calcularRaices(): String {
        val discriminante = discriminante()
        val solucion: String

        // Si el discriminante es cero, una única raíz real
        if (discriminante == 0.0) {
            val x = -b / (2 * a)
            solucion = "X1 = X2 = $x"
        }
        // Si el discriminante es mayor que cero, dos raíces reales distintas
        else if (discriminante > 0) {
            val x1 = (-b + sqrt(discriminante)) / (2 * a)
            val x2 = (-b - sqrt(discriminante)) / (2 * a)
            solucion = "X1 = $x1 , X2 = $x2"
        }
        // Si el discriminante es negativo, soluciones complejas
        else {
            val parteReal = -b / (2 * a)
            val parteImag = sqrt(-discriminante) / (2 * a)
            solucion = "X1 = $parteReal + ${parteImag}i, X2 = $parteReal - ${parteImag}i"
        }

        return solucion
    }
}
