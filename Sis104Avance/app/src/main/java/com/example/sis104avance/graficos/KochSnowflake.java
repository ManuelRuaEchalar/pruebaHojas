package com.example.sis104avance.graficos;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import androidx.annotation.NonNull;

public class KochSnowflake extends View {

    private int[] colores;
    private int colorIndex = 0;

    public KochSnowflake(Context context) {
        super(context);
        colores = generarTonosColor(Color.CYAN, 6); // Usamos tonos de cian para el copo de nieve
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.BLACK);
        Paint paint = new Paint();
        paint.setStrokeWidth(2);

        int ancho = canvas.getWidth();
        int alto = canvas.getHeight();

        // Calculamos los puntos del triángulo equilátero inicial
        float side = Math.min(ancho, alto) * 0.8f;
        float height = (float) (Math.sqrt(3) * side / 2);
        float x1 = (ancho - side) / 2;
        float y1 = (alto + height) / 2;
        float x2 = x1 + side;
        float y2 = y1;
        float x3 = x1 + side / 2;
        float y3 = y1 - height;

        // Dibujamos los tres lados del copo de nieve
        drawKochSide(canvas, x1, y1, x2, y2, 5, paint);
        drawKochSide(canvas, x2, y2, x3, y3, 5, paint);
        drawKochSide(canvas, x3, y3, x1, y1, 5, paint);
    }

    private void drawKochSide(Canvas canvas, float x1, float y1, float x5, float y5, int nivel, Paint paint) {
        if (nivel == 0) {
            paint.setColor(colores[colorIndex % colores.length]);
            colorIndex++;
            canvas.drawLine(x1, y1, x5, y5, paint);
        } else {
            float deltaX = x5 - x1;
            float deltaY = y5 - y1;

            float x2 = x1 + deltaX / 3;
            float y2 = y1 + deltaY / 3;

            float x3 = (float) (0.5 * (x1 + x5) + Math.sqrt(3) * (y1 - y5) / 6);
            float y3 = (float) (0.5 * (y1 + y5) + Math.sqrt(3) * (x5 - x1) / 6);

            float x4 = x1 + 2 * deltaX / 3;
            float y4 = y1 + 2 * deltaY / 3;

            drawKochSide(canvas, x1, y1, x2, y2, nivel - 1, paint);
            drawKochSide(canvas, x2, y2, x3, y3, nivel - 1, paint);
            drawKochSide(canvas, x3, y3, x4, y4, nivel - 1, paint);
            drawKochSide(canvas, x4, y4, x5, y5, nivel - 1, paint);
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