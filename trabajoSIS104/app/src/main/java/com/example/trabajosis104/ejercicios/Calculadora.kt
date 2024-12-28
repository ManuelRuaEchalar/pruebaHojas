package com.example.trabajosis104.ejercicios

data class Calculadora(val num1: Double, val num2: Double){
    fun adicionar(): Double = num1 + num2
    fun restar(): Double = num1 - num2
    fun multiplicar(): Double = num1 * num2
    fun dividir(): Double? = if (num2 != 0.0) num1 / num2 else null

}
