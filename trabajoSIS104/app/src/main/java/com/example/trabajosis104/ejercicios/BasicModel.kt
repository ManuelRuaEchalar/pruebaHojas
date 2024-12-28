// BasicModel.kt
package com.example.trabajosis104.ejercicios

class BasicModel {
    fun performComplexAddition(a: Int, b: Int): Triple<String, String, Int> {
        var currentA = a
        var currentB = b
        var totalSum = 0
        var totalAddition = 0
        var ar = 1
        var aa = 1
        val additionsBuilder = StringBuilder()

        while (currentA > 0 || currentB > 0) {
            val ultA = currentA % 10
            val ultB = currentB % 10
            val aYb = ultA + ultB
            val ultSuma = aYb % 10
            val ultAd = (aYb - ultSuma) / 10

            totalSum += ultSuma * ar
            if (ultAd > 0) {
                totalAddition += ultAd * aa
                additionsBuilder.insert(0, ultAd)
            } else {
                additionsBuilder.insert(0, " ")
            }

            currentA /= 10
            currentB /= 10
            ar *= 10
            aa *= 10
        }

        return Triple(additionsBuilder.toString(), totalSum.toString(), totalAddition)
    }
}
