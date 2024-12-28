package com.example.trabajosis104.ejercicios

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlin.math.pow

class Grafico2d(context: Context) : View(context) {

    private val graficoViewModel = ViewModelProvider(context as Grafico2dActivity).get(GraficoViewModel::class.java)

    companion object {
        const val TAG = "Grafico2d"
    }

    // Dimensiones del triángulo
    private var lado = 500f
    private var h = 0.0
    private var h2 = 0.0

    init {
        // Observar los cambios en el ViewModel
        graficoViewModel.alturaTriangulo.observe(context as Grafico2dActivity, Observer { nuevaAltura ->
            h = nuevaAltura
            invalidate() // Redibujar el gráfico cuando la altura cambia
        })

        graficoViewModel.alturaIntermedia.observe(context, Observer { nuevaAlturaIntermedia ->
            h2 = nuevaAlturaIntermedia
            invalidate()
        })

        // Realizar los cálculos iniciales
        graficoViewModel.calcularAlturaTriangulo(lado)
        graficoViewModel.calcularAlturaIntermedia(lado)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Fondo negro
        canvas.drawColor(Color.BLACK)

        // Configuración de Paint
        val paint = Paint().apply {
            color = Color.WHITE
            strokeWidth = 10f
        }

        // Configuración de Paint para el texto
        val textPaint = Paint().apply {
            color = Color.RED
            textSize = 60f  // Tamaño del texto
        }

        // Dimensiones del canvas
        val ancho = canvas.width.toFloat()
        val alto = canvas.height.toFloat()
        Log.d(TAG, "onDraw: ancho: $ancho alto: $alto")

        // Dibujar ejes X y Y
        canvas.drawLine(0f, alto / 2, ancho, alto / 2, paint) // Eje X
        canvas.drawLine(ancho / 2, 0f, ancho / 2, alto, paint) // Eje Y

        // Posición de inicio
        val inicioX = (ancho / 2) - (lado / 2)
        val inicioY = alto / 2

        // Color amarillo para el triángulo
        paint.color = Color.YELLOW

        // Dibujar líneas del triángulo
        canvas.drawLine(inicioX, inicioY, inicioX + lado, inicioY, paint) // Base
        canvas.drawLine(inicioX, inicioY, inicioX + lado / 2, inicioY - h.toFloat(), paint) // Lado izquierdo
        canvas.drawLine(inicioX + lado, inicioY, inicioX + lado / 2, inicioY - h.toFloat(), paint) // Lado derecho

        // Vértices del triángulo
        val verticeA = Pair(inicioX + lado / 2, inicioY - h.toFloat()) // Vértice superior
        val verticeB = Pair(inicioX, inicioY) // Vértice izquierdo
        val verticeC = Pair(inicioX + lado, inicioY) // Vértice derecho

        // Dibujar etiquetas A, B y C en los vértices del triángulo
        canvas.drawText("A", verticeA.first - 30, verticeA.second - 20, textPaint) // Etiqueta A
        canvas.drawText("B", verticeB.first - 70, verticeB.second + 70, textPaint) // Etiqueta B
        canvas.drawText("C", verticeC.first + 30, verticeC.second + 70, textPaint) // Etiqueta C

        paint.color = Color.BLUE
        paint.strokeWidth = 8f

        // Calcular las posiciones para las líneas cortas
        val medioBaseX = inicioX + lado / 2
        val medioBaseY = inicioY
        val medioIzquierdaX = inicioX + lado / 4
        val medioIzquierdaY = inicioY - h2.toFloat()
        val medioDerechaX = inicioX + 3 * lado / 4
        val medioDerechaY = inicioY - h2.toFloat()

        // Dibujar líneas cortas en las mitades de los lados
        // Línea corta en la base
        canvas.drawLine(medioBaseX, medioBaseY - 30, medioBaseX, medioBaseY + 30, paint) // Línea recta

        // Línea corta en la mitad izquierda
        canvas.drawLine(medioIzquierdaX - 20 * Math.cos(Math.toRadians(30.0)).toFloat(),
            medioIzquierdaY - 20 * Math.sin(Math.toRadians(30.0)).toFloat(),
            medioIzquierdaX + 20 * Math.cos(Math.toRadians(30.0)).toFloat(),
            medioIzquierdaY + 20 * Math.sin(Math.toRadians(30.0)).toFloat(),
            paint)

        // Línea corta en la mitad derecha
        canvas.drawLine(medioDerechaX - 20 * Math.cos(Math.toRadians(30.0)).toFloat(),
            medioDerechaY + 20 * Math.sin(Math.toRadians(30.0)).toFloat(),
            medioDerechaX + 20 * Math.cos(Math.toRadians(30.0)).toFloat(),
            medioDerechaY - 20 * Math.sin(Math.toRadians(30.0)).toFloat(),
            paint)
    }
}
