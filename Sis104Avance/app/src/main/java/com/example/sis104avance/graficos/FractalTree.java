package com.example.sis104avance.graficos;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import androidx.annotation.NonNull;

public class FractalTree extends View {

    private int[] colores;
    private int colorIndex = 0;

    public FractalTree(Context context) {
        super(context);
        colores = generarTonosColor(Color.GREEN, 8); // Usamos tonos de verde para el árbol
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.BLACK);
        Paint paint = new Paint();
        paint.setStrokeWidth(2);

        int ancho = canvas.getWidth();
        int alto = canvas.getHeight();

        // Comenzamos el árbol desde la parte inferior central de la pantalla
        drawTree(canvas, ancho / 2, alto - 50, -90, 200, 9, paint);
    }

    private void drawTree(Canvas canvas, float x1, float y1, float angle, float length, int nivel, Paint paint) {
        if (nivel == 0) {
            return;
        }

        // Calculamos el punto final de la rama
        float x2 = x1 + (float)(Math.cos(Math.toRadians(angle)) * length);
        float y2 = y1 + (float)(Math.sin(Math.toRadians(angle)) * length);

        // Dibujamos la rama
        paint.setColor(colores[colorIndex % colores.length]);
        colorIndex++;
        canvas.drawLine(x1, y1, x2, y2, paint);

        // Reducimos la longitud para las siguientes ramas
        float newLength = length * 0.7f;

        // Dibujamos las ramas izquierda y derecha
        drawTree(canvas, x2, y2, angle - 30, newLength, nivel - 1, paint);
        drawTree(canvas, x2, y2, angle + 30, newLength, nivel - 1, paint);
    }

    private int[] generarTonosColor(int colorBase, int cantidad) {
        int[] paleta = new int[cantidad];
        float[] hsl = new float[3];
        Color.colorToHSV(colorBase, hsl);
        float hue = hsl[0];
        float saturation = hsl[1];

        for (int i = 0; i < cantidad; i++) {
            float lightness = 0.3f + 0.5f * i / (cantidad - 1); // Ajustado para obtener tonos más oscuros y claros
            paleta[i] = Color.HSVToColor(new float[]{hue, saturation, lightness});
        }

        return paleta;
    }
}