package com.example.trabajosis104.ejercicios

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.trabajosis104.R

class ManualSumActivity : AppCompatActivity() {

    private val viewModel: ManualSumViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manual_sum)

        val input1 = findViewById<EditText>(R.id.editTextNumberA)
        val input2 = findViewById<EditText>(R.id.editTextNumberB)
        val resultTextView = findViewById<TextView>(R.id.resultTextView)
        val numATextView = findViewById<TextView>(R.id.numATextView)
        val numBTextView = findViewById<TextView>(R.id.numBTextView)
        val detailsTextView = findViewById<TextView>(R.id.detailsTextView)
        val calculateButton = findViewById<Button>(R.id.calculateButton)

        viewModel.details.observe(this, Observer { details ->
            detailsTextView.text = "Adiciones: $details"
        })

        viewModel.numA.observe(this, Observer { numA ->
            numATextView.text = "Número A: $numA"
        })

        viewModel.numB.observe(this, Observer { numB ->
            numBTextView.text = "Número B: $numB"
        })

        viewModel.result.observe(this, Observer { result ->
            resultTextView.text = "Resultado: $result"
        })

        calculateButton.setOnClickListener {
            val numberA = input1.text.toString().toIntOrNull() ?: 0
            val numberB = input2.text.toString().toIntOrNull() ?: 0
            viewModel.calculateManualSum(numberA, numberB)
        }
    }
}
