package com.example.trabajosis104.ejercicios

class StringValidationModel {
    fun validateString(input: String): Pair<String, String> {
        // Permite solo letras y espacios, y corta en el primer carácter no permitido
        val regex = Regex("^[a-zA-Z ]+$")
        val regexPartial = Regex("^[a-zA-Z ]*")

        val match = regexPartial.find(input)?.value ?: ""
        val formattedString = match.split(" ")
            .filter { it.isNotEmpty() }
            .joinToString(" ") { it.capitalize() }

        // Verifica si está correctamente formateada (con mayúsculas iniciales)
        val isCorrectFormat = input == formattedString

        // Validación completa: solo letras y espacios y además formato correcto
        val isValid = regex.matches(input) && isCorrectFormat

        return if (isValid) {
            "OK" to ""  // La cadena es válida, devolver "" en Salida2
        } else {
            "Error" to formattedString  // Si es incorrecto, devuelve la cadena formateada correctamente en Salida2
        }
    }
}
