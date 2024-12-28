package com.example.trabajosis104.ejercicios

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.trabajosis104.R

class EcuacionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ecuacion)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Casting explícito al tipo adecuado
        val editTextA = findViewById<EditText>(R.id.editTextA)
        val editTextB = findViewById<EditText>(R.id.editTextB)
        val editTextC = findViewById<EditText>(R.id.editTextTextC)
        val textViewResultadoEcuacion = findViewById<TextView>(R.id.textViewResultadoEcuacion)
        val buttonResolverEcuacion = findViewById<Button>(R.id.buttonResolverEcuacion)
        val buttonSalirEcuacion = findViewById<Button>(R.id.buttonSalirEcuacion)

        buttonResolverEcuacion.setOnClickListener {
            // Lógica para resolver la ecuación
            val valorA = editTextA.text.toString().toDoubleOrNull()
            val valorB = editTextB.text.toString().toDoubleOrNull()
            val valorC = editTextC.text.toString().toDoubleOrNull()

            if (valorA == null || valorB == null || valorC == null) {
                val errores = mutableListOf<String>()

                if (valorA == null) {
                    errores.add("A")
                }
                if (valorB == null) {
                    errores.add("B")
                }
                if (valorC == null) {
                    errores.add("C")
                }

                textViewResultadoEcuacion.text = "Por favor, ingrese valores válidos en: ${errores.joinToString(", ")}"
                //Toast.makeText(this, "Valores inválidos: ${errores.joinToString(", ")}", Toast.LENGTH_SHORT).show()
            } else {
                // Si los valores son válidos, resolver la ecuación
                val ecuacion = Ecuacion(valorA, valorB, valorC)
                val resultado = ecuacion.calcularRaices()

                textViewResultadoEcuacion.text = resultado
                Toast.makeText(this, resultado, Toast.LENGTH_SHORT).show()
            }
        }

        buttonSalirEcuacion.setOnClickListener {
            finish() // Finaliza la actividad y cierra la pantalla actual
        }
    }
}
