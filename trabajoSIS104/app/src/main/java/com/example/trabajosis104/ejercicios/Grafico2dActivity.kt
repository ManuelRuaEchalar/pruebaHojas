package com.example.trabajosis104.ejercicios

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

class Grafico2dActivity : AppCompatActivity() {

    private val graficoViewModel: GraficoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(Grafico2d(this))
    }
}
