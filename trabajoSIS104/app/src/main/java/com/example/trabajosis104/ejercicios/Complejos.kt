package com.example.trabajosis104.ejercicios

import java.math.BigDecimal
import java.math.RoundingMode

class Complejos(var a: Double, var b: Double, var c: Double, var d: Double) {

    // Getters y Setters en Kotlin se generan automáticamente al declarar propiedades var/val

    fun suma(): String {
        val real = this.a + this.c
        val imag = this.b + this.d
        return if (imag < 0) {
            "$real - ${Math.abs(imag)} i"
        } else {
            "$real + $imag i"
        }
    }

    fun resta(): String {
        val real = this.a - this.c
        val imag = this.b - this.d
        return if (imag < 0) {
            "$real - ${Math.abs(imag)} i"
        } else {
            "$real + $imag i"
        }
    }

    fun multiplicar(): String {
        val real = (this.a * this.c) - (this.b * this.d)
        val imag = (this.a * this.d) + (this.c * this.b)
        return if (imag < 0) {
            "$real - ${Math.abs(imag)} i"
        } else {
            "$real + $imag i"
        }
    }

    fun dividir(): String {
        val aux = this.c * this.c + this.d * this.d
        var real = (this.a * this.c + this.b * this.d) / aux
        var imag = (this.b * this.c - this.a * this.d) / aux

        // Redondear a dos decimales usando BigDecimal
        real = BigDecimal(real.toString()).setScale(2, RoundingMode.HALF_UP).toDouble()
        imag = BigDecimal(imag.toString()).setScale(2, RoundingMode.HALF_UP).toDouble()

        return if (imag < 0) {
            "$real - ${Math.abs(imag)} i"
        } else {
            "$real + $imag i"
        }
    }
}
