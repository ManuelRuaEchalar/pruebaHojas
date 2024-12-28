// Person.kt
package com.example.trabajosis104.ejercicios

// Data class para la persona
data class Person(val name: String, val email: String) {
    // Clase interna para detalles
    inner class PersonDetails(val age: Int, val address: String) {
        fun mostrarDetalles() {
            println("Edad: $age, Dirección: $address")
        }
    }

    // Clase anidada para categoría
    class Categoria(val nombreCategoria: String) {
        fun mostrarCategoria() {
            println("Categoría: $nombreCategoria")
        }
    }

    // companion object para información estática
    companion object {
        const val tipoPersona = "Usuario"
        fun mostrarSaludo() {
            println("Bienvenido al sistema de gestión de usuarios.")
        }
    }
}

// Singleton para conexión de base de datos
object DatabaseConnection {
    var estado: String = "Desconectado"

    fun conectar() {
        estado = "Conectado"
        println("Conexión a la base de datos establecida.")
    }

    fun desconectar() {
        estado = "Desconectado"
        println("Desconexión de la base de datos.")
    }
}
