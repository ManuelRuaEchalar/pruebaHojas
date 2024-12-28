package com.example.trabajosis104.ejercicios

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.trabajosis104.R

class SumaActivity : AppCompatActivity() {

    private val sumaViewModel: SumaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_suma)

        val btnCalcular = findViewById<Button>(R.id.btnCalcular)
        val tvResultado = findViewById<TextView>(R.id.tvResultado)

        // Observar los cambios en el resultado
        sumaViewModel.resultado.observe(this, Observer { resultado ->
            tvResultado.text = resultado
        })

        btnCalcular.setOnClickListener {
            val aInput = findViewById<EditText>(R.id.aInput).text.toString().toIntOrNull() ?: 0
            val bInput = findViewById<EditText>(R.id.bInput).text.toString().toIntOrNull() ?: 0
            sumaViewModel.calcularSuma(aInput, bInput)
        }
    }
}
