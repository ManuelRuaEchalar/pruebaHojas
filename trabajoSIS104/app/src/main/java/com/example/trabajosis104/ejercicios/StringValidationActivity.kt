// StringValidationActivity.kt
package com.example.trabajosis104.ejercicios

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.trabajosis104.R

class StringValidationActivity : AppCompatActivity() {
    private val viewModel: StringValidationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_string_validation)

        val inputText = findViewById<EditText>(R.id.editTextInput)
        val resultTextView = findViewById<TextView>(R.id.resultTextView)
        val formattedTextView = findViewById<TextView>(R.id.formattedTextView)
        val validateButton = findViewById<Button>(R.id.validateButton)

        viewModel.validationResult.observe(this) { result ->
            resultTextView.text = "Resultado: $result"
        }

        viewModel.formattedString.observe(this) { formatted ->
            formattedTextView.text = "Formato: $formatted"
        }

        validateButton.setOnClickListener {
            val input = inputText.text.toString()
            viewModel.validateInput(input)
        }
    }
}
