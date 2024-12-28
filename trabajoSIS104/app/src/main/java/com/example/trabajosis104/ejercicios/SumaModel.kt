package com.example.trabajosis104.ejercicios

class SumaModel {

    // Función recursiva para sumar dos números
    fun sumaRecursiva(a: Int, b: Int): Int {
        return if (b == 0) {
            a
        } else {
            sumaRecursiva(a + 1, b - 1)
        }
    }
}
