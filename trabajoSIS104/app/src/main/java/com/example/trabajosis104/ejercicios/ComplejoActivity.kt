package com.example.trabajosis104.ejercicios

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.trabajosis104.R

class ComplejoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_complejo)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val editTextA = findViewById<EditText>(R.id.editTextA)
        val editTextB = findViewById<EditText>(R.id.editTextB)
        val editTextC = findViewById<EditText>(R.id.editTextC)
        val editTextD = findViewById<EditText>(R.id.editTextD)

        val buttonComplejos = findViewById<Button>(R.id.buttonComplejos)
        val textViewResultado = findViewById<TextView>(R.id.textViewResultado)

        buttonComplejos.setOnClickListener {
            val aString = editTextA.text.toString()
            val bString = editTextB.text.toString()
            val cString = editTextC.text.toString()
            val dString = editTextD.text.toString()

            val errores = mutableListOf<String>()

            // Verifica si los campos están vacíos o contienen valores no válidos
            val a = try {
                aString.toDouble()
            } catch (e: NumberFormatException) {
                errores.add("A")
                null
            }

            val b = try {
                bString.toDouble()
            } catch (e: NumberFormatException) {
                errores.add("B")
                null
            }

            val c = try {
                cString.toDouble()
            } catch (e: NumberFormatException) {
                errores.add("C")
                null
            }

            val d = try {
                dString.toDouble()
            } catch (e: NumberFormatException) {
                errores.add("D")
                null
            }

            // Si hay errores, muestra un mensaje indicando los campos inválidos
            if (errores.isNotEmpty()) {
                textViewResultado.text = "Por favor, ingrese valores válidos en: ${errores.joinToString(", ")}"
            } else {
                // Si los valores son válidos, realiza los cálculos
                val complejo1 = Complejos(a!!, b!!, c!!, d!!)
                val resultadoSuma = complejo1.suma()
                val resultadoResta = complejo1.resta()
                val resultadoMultiplicacion = complejo1.multiplicar()
                val resultadoDivision = complejo1.dividir()

                textViewResultado.text = "Suma: $resultadoSuma \n Resta: $resultadoResta \n Multiplicación: $resultadoMultiplicacion \n División: $resultadoDivision"
            }
        }

    }
}