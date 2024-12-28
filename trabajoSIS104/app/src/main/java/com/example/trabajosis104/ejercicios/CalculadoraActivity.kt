package com.example.trabajosis104.ejercicios

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.example.trabajosis104.R

class CalculadoraActivity : AppCompatActivity() {

    private val viewModel: CalculadoraViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_calculadora2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val editText1 = findViewById<EditText>(R.id.editTextNumber1)
        val editText2 = findViewById<EditText>(R.id.editTextNumber2)
        val buttonCalculadora = findViewById<Button>(R.id.buttonCalculadora)
        val textViewResultado = findViewById<TextView>(R.id.textViewResultado)

        viewModel.resultado.observe(this, Observer { result ->
            textViewResultado.text = result
        })
        buttonCalculadora.setOnClickListener {
            val num1 = editText1.text.toString().toDouble()
            val num2 = editText2.text.toString().toDouble()
            viewModel.calcular(num1, num2)
        }
    }
}