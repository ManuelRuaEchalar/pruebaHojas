package com.example.trabajosis104.ejercicios

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.trabajosis104.R

class SerieActivity : AppCompatActivity() {

    private val serieViewModel: SerieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_serie)

        val btnCalcular = findViewById<Button>(R.id.btnCalcular)
        val tvResultado = findViewById<TextView>(R.id.tvResultado)

        // Observar los cambios en el resultado
        serieViewModel.resultado.observe(this, Observer { resultado ->
            tvResultado.text = resultado
        })

        btnCalcular.setOnClickListener {
            val kMaxInput = findViewById<EditText>(R.id.kMaxInput).text.toString().toIntOrNull() ?: 0
            serieViewModel.calcularSerie(kMaxInput)
        }
    }
}