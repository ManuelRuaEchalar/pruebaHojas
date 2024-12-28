package com.example.trabajosis104.ejercicios

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.trabajosis104.R

class MultiplicacionActivity : AppCompatActivity() {

    private val multiplicacionViewModel: MultiplicacionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multiplicacion)

        val editTextA = findViewById<EditText>(R.id.editTextA)
        val editTextB = findViewById<EditText>(R.id.editTextTB)
        val buttonMultiplicar = findViewById<Button>(R.id.buttonMultiplicar)
        val textViewA = findViewById<TextView>(R.id.textViewA)
        val textViewB = findViewById<TextView>(R.id.textViewB)
        val textViewResultadoA = findViewById<TextView>(R.id.textViewResultadoA)
        val textViewResultadoB = findViewById<TextView>(R.id.textViewResultadoB)
        val textViewResultadoFinal = findViewById<TextView>(R.id.textViewResultadoFinal)

        // Observa los cambios en el ViewModel y actualiza la interfaz
        multiplicacionViewModel.multiplicacion.observe(this, Observer { resultado ->
            textViewA.text = "${resultado.A}"
            textViewB.text = "${resultado.B}"
            textViewResultadoA.text = "${resultado.resultadoA}"
            textViewResultadoB.text = "${resultado.resultadoB}"
            textViewResultadoFinal.text = "${resultado.resultadoFinal}"
        })

        // Maneja el evento del botón
        buttonMultiplicar.setOnClickListener {
            val A = editTextA.text.toString()
            val B = editTextB.text.toString()
            multiplicacionViewModel.realizarMultiplicacion(A, B)
        }
    }
}
