package com.example.trabajosis104.ejercicios

class DivisionModel {
    fun dividirPorRestasSucesivas(numerador: Int, denominador: Int, decimales: Int): String {
        if (denominador == 0) throw IllegalArgumentException("El denominador no puede ser 0")

        var respuestas = divisionCiclica(numerador, denominador)
        var particiones = respuestas[0]
        var multiplicaciones = respuestas[1]
        var respuestaFinal = respuestas[2]
        var resultadoEntero = respuestaFinal[0]

        var vistaProceso = "${particiones[0] }                        ${resultadoEntero}\n - ${multiplicaciones[0]} \n ------------------- \n ${particiones[1]} \n" +
                " - ${multiplicaciones[1]} \n" +
                " ------------------- \n ${particiones[2]} \n" +
                " - ${multiplicaciones[2]} \n" +
                " ------------------- \n ${respuestaFinal[1]}"

        return vistaProceso
    }

    fun partir(divisor: Int, dividendo: Int): Array<Int> {
        var divisor = divisor
        var dividendo = dividendo
        var anterior = dividendo
        var sobrante = 0
        var aumento = 1
        var digito = 0
        while(dividendo>divisor){
            anterior = dividendo
            if((dividendo-(dividendo%10))/10 > divisor){
                digito =dividendo%10
                digito*=aumento
                sobrante+=digito
                aumento*=10
            }
            dividendo = (dividendo-(dividendo%10))/10

        }
        var partes = Array(2){0}
        partes[0] = anterior
        partes[1] = sobrante
        return partes
    }

    fun dividir(dividendo: Int, divisor: Int): Array<Int> {
        var dividendo = dividendo
        var divisor = divisor
        var contador = 0
        var residuo = 0

        while (dividendo > divisor) {
            dividendo = dividendo - divisor
            contador++

        }
        residuo = dividendo
        var resultado = Array(2){0}
        resultado[0] = contador
        resultado[1] = residuo
        return resultado

    }

    fun divisionCompleta(dividendo: Int, divisor: Int) {
        var dividendo = dividendo
        var divisor = divisor
        var partido = partir(divisor, dividendo)
        println("Se toma ${partido[0]} y se aparta ${partido[1]}")
        dividendo = partido[0]
        var resta = dividir(dividendo,divisor)
        println("El resultado es ${resta[0]} y sobra ${resta[1]}")
        var aumento = 1
        var i = contarDigitos(partido[1])
        if(partido[1]>0){

            while (i>0){
                aumento*=10
                i--

            }
        }
        println("aumento es ${aumento}")
        dividendo = resta[1]*aumento
        println("div es ${dividendo}")
        dividendo = dividendo+partido[1]
        println("El nuevo dividendo es: ${dividendo}")
        println("*************************************")
        if(dividendo > divisor){
            divisionCompleta(dividendo, divisor)
        }

    }

    fun divisionCiclica(dividendo: Int, divisor: Int):Array<Array<Int>>{
        var total = 0
        var expansor = 10
        var dividendo = dividendo
        var divisor = divisor
        var particiones = Array(contarDigitos(dividendo)){0}
        var multiplicaciones = Array(particiones.size){0}
        var respuestaFinal = Array(particiones.size){0}
        var l = 0

        var k = 0

        while(dividendo>divisor){
            var partido = partir(divisor, dividendo)
            println("Se toma ${partido[0]} y se aparta ${partido[1]}")
            particiones[l] = partido[0]
            dividendo = partido[0]
            var resta = dividir(dividendo,divisor)
            var multiplo = resta[0]*divisor
            multiplicaciones[l] = multiplo
            println("${partido[0]} - ${multiplo}")
            println("El resultado es ${resta[0]} y sobra ${resta[1]}")
            respuestaFinal[1] = resta[1]
            total+=resta[0]
            total*=10
            println("total: ${total}")

            var aumento = 1
            var i = contarDigitos(partido[1])
            if(partido[1]>0){

                while (i>0){
                    aumento*=10
                    i--

                }
            }
            println("aumento es ${aumento}")
            dividendo = resta[1]*aumento
            println("div es ${dividendo}")
            dividendo = dividendo+partido[1]
            println("El nuevo dividendo es: ${dividendo}")
            println("*************************************")
            l++
        }


        total/=10
        respuestaFinal[0]=total
        var salida = Array(3){Array(particiones.size){0}}
        salida[0]=particiones
        salida[1]=multiplicaciones
        salida[2]=respuestaFinal
        println("Resultado final: ${total}")
        return salida
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





}