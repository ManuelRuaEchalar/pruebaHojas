package com.example.eco.ui.games

data class Minijuego(
    var id: Int = 0, // ID único (autogenerado)
    var nombre: String, // Nombre del minijuego
    var desbloqueado: Boolean // Estado de desbloqueo (0 o 1 en la base de datos)
)
