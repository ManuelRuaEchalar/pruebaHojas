package com.example.trabajosis104.baseDatos

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.trabajosis104.R

class BaseDatosActivity : AppCompatActivity() {
    lateinit var dbHelper: DBHelper
    lateinit var editTextId: EditText
    lateinit var editTextNombre: EditText
    lateinit var editTextDescripcion: EditText

    lateinit var buttonAgregar: Button
    lateinit var buttonListar: Button
    lateinit var buttonActualizar: Button
    lateinit var buttonEliminar: Button

    lateinit var textViewResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_base_datos)

        dbHelper = DBHelper(this)
        editTextId = findViewById(R.id.editTextId)
        editTextNombre = findViewById(R.id.editTextNombre)
        editTextDescripcion = findViewById(R.id.editTextDescripcion)

        buttonAgregar = findViewById(R.id.buttonAgregar)
        buttonListar = findViewById(R.id.buttonListar)

        buttonActualizar = findViewById(R.id.buttonActualizar)
        buttonEliminar = findViewById(R.id.buttonEliminar)

        textViewResultado = findViewById(R.id.textViewResultado)

        buttonAgregar.setOnClickListener {
            agregarLugar()
        }

        buttonListar.setOnClickListener {
            listarLugar()
        }

        buttonActualizar.setOnClickListener {
            actualizarLugar()
        }

        buttonEliminar.setOnClickListener {
            eliminarLugar()
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun agregarLugar(){
        val nombre = editTextNombre.text.toString()
        val descripcion = editTextDescripcion.text.toString()
        val lugar = Lugar(0,nombre,descripcion)
        dbHelper.insertarLugar(lugar)
    }

    fun listarLugar() {
        // Obtén la lista de lugares desde la base de datos
        val listaLugares = dbHelper.obtenerLugares()

        // Construye una cadena de texto con la información de los lugares
        val stringBuilder = StringBuilder()
        for (lugar in listaLugares) {
            stringBuilder.append("ID: ${lugar.id}\n")
            stringBuilder.append("Nombre: ${lugar.nombre}\n")
            stringBuilder.append("Descripción: ${lugar.descripcion}\n")
            stringBuilder.append("\n") // Añade una separación entre lugares
        }

        // Muestra el resultado en el TextView
        textViewResultado.text = stringBuilder.toString()
    }

    fun eliminarLugar() {
        val id = editTextId.text.toString().toInt()
        dbHelper.eliminarLugar(id)
    }

    fun actualizarLugar() {
        val id = editTextId.text.toString().toInt()
        val nombre = editTextNombre.text.toString()
        val descripcion = editTextDescripcion.text.toString()
        val lugar = Lugar(id, nombre, descripcion)

        dbHelper.actualizarLugar(lugar)
    }
}