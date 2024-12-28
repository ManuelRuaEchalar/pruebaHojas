package com.example.sis104avance.graficos;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

import androidx.annotation.NonNull;

public class Cantor extends View {

    private int[] colores;
    private int colorIndex = 0;

    public Cantor(Context context) {
        super(context);
        // Generar una paleta de tonos del mismo color
        colores = generarTonosColor(Color.RED, 6); // Cambia el color base y el número de tonos según lo necesites
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.BLACK);
        Paint paint = new Paint();

        int ancho = canvas.getWidth();
        int alto = canvas.getHeight();

        // Definir los vértices del triángulo inicial
        float[] punto1 = {ancho / 2, 50}; // Vértice superior
        float[] punto2 = {50, alto - 50}; // Vértice inferior izquierdo
        float[] punto3 = {ancho - 50, alto - 50}; // Vértice inferior derecho

        // Dibujar el Triángulo de Sierpinski
        drawSierpinski(canvas, punto1, punto2, punto3, 6, paint);
    }

    private void drawSierpinski(Canvas canvas, float[] p1, float[] p2, float[] p3, int nivel, Paint paint) {
        if (nivel == 0) {
            // Dibujar el triángulo actual
            Path path = new Path();
            path.moveTo(p1[0], p1[1]);
            path.lineTo(p2[0], p2[1]);
            path.lineTo(p3[0], p3[1]);
            path.close();

            // Cambiar color para cada nivel
            paint.setColor(colores[colorIndex % colores.length]);
            colorIndex++;
            canvas.drawPath(path, paint);
        } else {
            // Calcular los puntos medios de los lados del triángulo
            float[] midP1P2 = midpoint(p1, p2);
            float[] midP2P3 = midpoint(p2, p3);
            float[] midP3P1 = midpoint(p3, p1);

            // Llamadas recursivas para los tres triángulos más pequeños
            drawSierpinski(canvas, p1, midP1P2, midP3P1, nivel - 1, paint);
            drawSierpinski(canvas, midP1P2, p2, midP2P3, nivel - 1, paint);
            drawSierpinski(canvas, midP3P1, midP2P3, p3, nivel - 1, paint);
        }
    }

    // Calcular el punto medio entre dos puntos
    private float[] midpoint(float[] p1, float[] p2) {
        return new float[]{(p1[0] + p2[0]) / 2, (p1[1] + p2[1]) / 2};
    }

    // Generar una paleta de tonos de un solo color
    private int[] generarTonosColor(int colorBase, int cantidad) {
        int[] paleta = new int[cantidad];
        float[] hsl = new float[3];
        Color.colorToHSV(colorBase, hsl);
        float hue = hsl[0];
        float saturation = hsl[1];

        // Generar tonos variando la luminosidad
        for (int i = 0; i < cantidad; i++) {
            float lightness = 0.2f + 0.6f * i / (cantidad - 1); // Distribuir entre 20% y 80% de luminosidad
            paleta[i] = Color.HSVToColor(new float[]{hue, saturation, lightness});
        }

        return paleta;
    }
}
