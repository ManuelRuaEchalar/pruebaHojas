package com.example.trabajosis104

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.trabajosis104.baseDatos.BaseDatosActivity
import com.example.trabajosis104.ejercicios.EjerciciosActivity
import com.example.trabajosis104.ejercicios.Grafico2dActivity
import com.example.trabajosis104.examen.ExamenActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val buttonEjercicios = findViewById<Button>(R.id.buttonEjercicios)
        val buttonExamen = findViewById<Button>(R.id.buttonExamen)
        val buttonBaseDatos = findViewById<Button>(R.id.buttonBaseDatos)
        val buttonGrafico = findViewById<Button>(R.id.buttonGrafico)

        buttonEjercicios.setOnClickListener {
            val intent = Intent(this, EjerciciosActivity::class.java)
            startActivity(intent)
        }
        buttonExamen.setOnClickListener {
            val intent = Intent(this, ExamenActivity::class.java)
            startActivity(intent)
        }


        buttonBaseDatos.setOnClickListener {
            val intent = Intent(this, BaseDatosActivity::class.java)
            startActivity(intent)
        }

        buttonGrafico.setOnClickListener {
            val intent = Intent(this, Grafico2dActivity::class.java)
            startActivity(intent)
        }

    }
}