package com.example.sis104avance.graficos;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import androidx.annotation.NonNull;

public class SierpinskiCarpet extends View {

    private int[] colores;
    private int colorIndex = 0;
    private int nivelMaximo = 5;

    public SierpinskiCarpet(Context context) {
        super(context);
        colores = generarTonosColor(Color.BLUE, 6); // Usamos tonos de azul para la alfombra
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.BLACK);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);

        int ancho = canvas.getWidth();
        int alto = canvas.getHeight();
        int tamanio = Math.min(ancho, alto);

        // Comenzamos con un cuadrado que ocupa toda la pantalla
        dibujarAlfombra(canvas, 0, 0, tamanio, 0, paint);
    }

    private void dibujarAlfombra(Canvas canvas, float x, float y, float tamanio, int nivel, Paint paint) {
        if (nivel > nivelMaximo) {
            return;
        }

        float tercio = tamanio / 3;

        // Dibujamos el cuadrado central
        paint.setColor(colores[colorIndex % colores.length]);
        colorIndex++;
        canvas.drawRect(x + tercio, y + tercio, x + 2 * tercio, y + 2 * tercio, paint);

        // Recursivamente dibujamos los 8 cuadrados restantes
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) continue; // Saltamos el cuadrado central

                float newX = x + i * tercio;
                float newY = y + j * tercio;
                dibujarAlfombra(canvas, newX, newY, tercio, nivel + 1, paint);
            }
        }
    }

    private int[] generarTonosColor(int colorBase, int cantidad) {
        int[] paleta = new int[cantidad];
        float[] hsl = new float[3];
        Color.colorToHSV(colorBase, hsl);
        float hue = hsl[0];
        float saturation = hsl[1];

        for (int i = 0; i < cantidad; i++) {
            float lightness = 0.5f + 0.5f * i / (cantidad - 1);
            paleta[i] = Color.HSVToColor(new float[]{hue, saturation, lightness});
        }

        return paleta;
    }
}