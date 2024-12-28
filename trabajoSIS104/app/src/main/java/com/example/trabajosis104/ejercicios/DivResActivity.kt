package com.example.trabajosis104.ejercicios

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.trabajosis104.R

class DivResActivity : AppCompatActivity() {

    private val divisionViewModel: DivisionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_div_res)

        val btnCalcular = findViewById<Button>(R.id.btnCalcular)
        val tvResultado = findViewById<TextView>(R.id.tvResultado)

        divisionViewModel.resultado.observe(this, Observer { resultado ->
            tvResultado.text = resultado
        })

        btnCalcular.setOnClickListener {
            val numerador = findViewById<EditText>(R.id.numeradorInput).text.toString().toIntOrNull() ?: 0
            val denominador = findViewById<EditText>(R.id.denominadorInput).text.toString().toIntOrNull() ?: 0
            val decimales = findViewById<EditText>(R.id.decimalesInput).text.toString().toIntOrNull() ?: 0

            divisionViewModel.dividir(numerador, denominador, decimales)
        }


    }
}