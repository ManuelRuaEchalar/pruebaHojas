// SumaMainActivity.kt
package com.example.trabajosis104.ejercicios

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.trabajosis104.R

class SumaMainActivity : AppCompatActivity() {
    private val viewModel: BasicViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_suma_main)

        val input1 = findViewById<EditText>(R.id.editTextNumber1)
        val input2 = findViewById<EditText>(R.id.editTextNumber2)
        val additionsTextView = findViewById<TextView>(R.id.additionsTextView)
        val numberATextView = findViewById<TextView>(R.id.numberATextView)
        val numberBTextView = findViewById<TextView>(R.id.numberBTextView)
        val resultTextView = findViewById<TextView>(R.id.resultTextView)
        val calculateButton = findViewById<Button>(R.id.calculateButton)

        viewModel.additions.observe(this) { additions ->
            additionsTextView.text = "Adiciones: $additions"
        }

        viewModel.numberA.observe(this) { numberA ->
            numberATextView.text = "Número A: $numberA"
        }

        viewModel.numberB.observe(this) { numberB ->
            numberBTextView.text = "Número B: $numberB"
        }

        viewModel.result.observe(this) { result ->
            resultTextView.text = "Resultado: $result"
        }

        calculateButton.setOnClickListener {
            val numberA = input1.text.toString().toIntOrNull() ?: 0
            val numberB = input2.text.toString().toIntOrNull() ?: 0
            viewModel.performAddition(numberA, numberB)
        }
    }
}