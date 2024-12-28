// PersonViewActivity.kt
package com.example.trabajosis104.ejercicios

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.activity.viewModels
import com.example.trabajosis104.R

class PersonViewActivity : AppCompatActivity() {
    private val personViewModel: PersonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_view)

        val editTextNombre = findViewById<EditText>(R.id.editTextNombre)
        val editTextEmail = findViewById<EditText>(R.id.editTextEmail)
        val buttonPerson = findViewById<Button>(R.id.buttonPerson)
        val textViewPerson = findViewById<TextView>(R.id.textViewPerson)

        personViewModel.person.observe(this, Observer { result ->
            textViewPerson.text = "Name: ${result.name} Email: ${result.email}"

            // Usando la inner class PersonDetails
            val details = result.PersonDetails(30, "Calle Falsa 123")
            details.mostrarDetalles()

            // Usando la clase anidada
            val categoria = Person.Categoria("Administrador")
            categoria.mostrarCategoria()
        })

        buttonPerson.setOnClickListener {
            val name = editTextNombre.text.toString()
            val email = editTextEmail.text.toString()

            // Uso de ? y let para manejar nulos
            name?.let { nombre ->
                email?.let { correo ->
                    personViewModel.updatePerson(nombre, correo)
                }
            }

            // Uso del companion object
            Person.mostrarSaludo()
        }

        // Singleton
        DatabaseConnection.conectar()
    }
}