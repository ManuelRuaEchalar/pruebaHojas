package com.example.trabajosis104.ejercicios

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalculadoraViewModel:ViewModel() {
    private var _resultado = MutableLiveData<String>()
    val resultado:LiveData<String> get()= _resultado

    fun calcular(num1:Double, num2:Double){
        val calculadora = Calculadora(num1,num2)
        val adicionar = calculadora.adicionar()
        val restar = calculadora.restar()
        val multiplicar = calculadora.multiplicar()
        val dividir = calculadora.dividir()

        val res = StringBuilder()
            .append("Suma:$adicionar\n ")
            .append("Resta: $restar\n")
            .append("Multiplicacion: $multiplicar\n")
            .append("Dividir ${dividir?:"No se puede dividir entre CERO"}")
            .toString()
        _resultado.value = res


    }

}