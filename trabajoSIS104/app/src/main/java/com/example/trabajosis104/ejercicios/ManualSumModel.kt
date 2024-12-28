package com.example.trabajosis104.ejercicios

class ManualSumModel {
    fun sumWithDetails(a: Int, b: Int): Pair<String, Int> {
        var num1 = a
        var num2 = b
        var totalSum = 0
        var totalAdd = 0
        var ar = 1
        var aa = 1
        var ultAd = 0
        val details = StringBuilder()

        while (num1 > 0 || num2 > 0) {
            val ultA = num1 % 10
            val ultB = num2 % 10
            val aYb = ultA + ultB + ultAd
            val ultSuma = aYb % 10
            ultAd = (aYb - ultSuma) / 10

            totalSum += ultSuma * ar
            totalAdd += ultAd * aa
            details.append(" $ultAd ")

            num1 = (num1 - ultA) / 10
            num2 = (num2 - ultB) / 10

            ar *= 10
            aa *= 10
        }

        return Pair(details.toString().trim(), totalSum)
    }
}
