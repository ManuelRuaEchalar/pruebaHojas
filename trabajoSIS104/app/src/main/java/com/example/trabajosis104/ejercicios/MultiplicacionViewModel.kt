package com.example.trabajosis104.ejercicios

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MultiplicacionViewModel : ViewModel() {
    private val _multiplicacion = MutableLiveData<Multiplicacion>()
    val multiplicacion: LiveData<Multiplicacion> get() = _multiplicacion

    init {
        _multiplicacion.value = Multiplicacion("", "", "", "", "")
    }

    fun realizarMultiplicacion(A: String, B: String) {
        // Convertimos los strings a enteros
        val numA = A.toInt()
        val numB = B.toInt()

        val numerosA = generarArreglo(numA)
        val numerosB = generarArreglo(numB)

        val resultado = multiplicar(numerosA, numerosB)
        val resultadoFinal = suma(resultado)



        // Actualizar LiveData
        _multiplicacion.value = Multiplicacion(
            A,
            B,
            resultado[0].toString(),
            resultado[1].toString(),
            resultadoFinal.toString()

        )
    }

    fun contarDigitos(n:Int): Int {
        var n = n
        var cantidadDigitos=0
        while(n>=10){
            n =(n-(n%10))/10
            cantidadDigitos++
        }
        cantidadDigitos++
        return cantidadDigitos
    }

    fun generarArreglo(n:Int):Array<Int>{
        var cantidadDigitos = contarDigitos(n)
        var n = n
        var digito = 0
        var numeros = Array(cantidadDigitos){0}
        var i = 0
        while(n>=10){
            digito = n%10
            n = (n-digito)/10
            numeros[i]=digito
            i++
        }
        numeros[i] = n
        return numeros

    }


    fun multiplicar(a:Array<Int>, b:Array<Int>): Array<Int> {
        var a = a
        var b = b
        var adicion = 0
        var valor = 0
        var resultados = Array(b.size){Array(a.size+1) {0}}
        for (i in 0 until b.size){
            println("***************************")

            adicion = 0
            for(j in 0 until a.size){
                valor = (b[i]*a[j]) + adicion
                if (valor >= 10) {
                    adicion = (valor - (valor % 10))/10
                    valor = valor%10

                }else{adicion=0}
                resultados[i][j] = valor
                println("se agrega: ${valor}")

            }
            resultados[i][a.size] = adicion

        }

        for (fila in resultados) {
            println(fila.joinToString())
        }
        println("las partes son: ")
        var partes = Array(b.size){0}
        for (i in 0 until b.size){
            partes[i] = generarEnteroVolteado(resultados[i])
        }
        for (fila in partes) {
            println(fila)
        }
        println("fin partes")
        return partes
    }

    fun iguales(n: Array<Int>): Boolean {
        if (n.isEmpty()) return true // Un array vacío se considera con todos los elementos iguales
        val primerElemento = n[0]
        for (i in n) {
            if (i != primerElemento) {
                return false
            }
        }
        return true
    }

    fun suma(numeros: Array<Int>): Int {
        val numeros = numeros
        var aumento=1
        for (i in 0 until numeros.size){
            numeros[i]*=aumento
            aumento*=10
        }

        for (fila in numeros) {
            println(fila)
        }

        val cdigitos = Array(numeros.size){0}
        for (i in 0 until cdigitos.size){
            cdigitos[i] = contarDigitos(numeros[i])
        }
        for (fila in cdigitos) {
            println(fila)
        }

        var numerosRellenos = Array(cdigitos.size){Array(mayor(cdigitos)){0}}
        for (fila in numerosRellenos) {
            println(fila.joinToString())
        }
        for (i in 0 until numerosRellenos.size){
            numerosRellenos[i] = generarArregloConCeros(numeros[i], numerosRellenos[0].size)
        }

        for (fila in numerosRellenos) {
            println(fila.joinToString())
        }
        aumento = 0
        var resultado = Array(numerosRellenos[0].size){0}
        var i = numerosRellenos.size-1
        var j = numerosRellenos[0].size-1
        println("filas" + i)
        println("digitos" + j)
        var valor = 0
        var adicion = 0

        while(j>=0){
            while(i>=0){
                valor += numerosRellenos[i][j]

                i--
            }
            valor += adicion
            println(valor)
            if (j!=0 && valor >= 10) {
                adicion = (valor - (valor % 10))/10
                valor = valor%10

            } else {adicion = 0}
            resultado[j] = valor
            valor = 0
            j--
            i = numerosRellenos.size-1
        }
        var rfinal = generarEntero(resultado)
        println("resultado final: ${rfinal}")
        return rfinal

    }

    fun generarEnteroVolteado(n: Array<Int>): Int {
        var n = n
        var entero = 0
        var ult=0
        var tope = 0
        var  i = n.size-1
        if(n[i]==0){i--}
        while (i>tope){
            entero += n[i]
            entero*=10
            i--
            ult=i
        }
        entero += n[ult]
        return entero
    }

    fun generarEntero(n: Array<Int>): Int {
        var n = n
        var entero = 0
        var ult=0
        for (i in 0 until n.size-1){
            entero += n[i]
            entero*=10
            ult=i
        }
        entero += n[ult+1]
        return entero
    }

    fun generarArregloConCeros(n:Int,t: Int):Array<Int>{

        var n = n
        var digito = 0
        var numeros = Array(t){0}
        var i = t-1
        while(i>=0){
            if(n>=10){
                digito = n%10
                n = (n-digito)/10
                numeros[i]=digito
            } else if (n>0 && n < 10) {
                numeros[i] = n
                n=0
            } else { numeros[i] = 0}

            i--
        }
        println("el vector es: ")
        for (fila in numeros) {
            println(fila)
        }
        return numeros

    }

    fun mayor(n: Array<Int>): Int {
        var n = n
        var mayor = 0
        for (i in 0 until n.size){
            if (n[i]>mayor){mayor=n[i]}

        }
        return mayor
    }
}
