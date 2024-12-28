package com.example.sis104avance.graficos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import androidx.annotation.NonNull;

public class Julia extends View {

    private Paint paint;
    private Bitmap bitmap;
    private static final double C_REAL = -0.7;  // Parte real del parámetro complejo
    private static final double C_IMAG = 0.27015; // Parte imaginaria del parámetro complejo

    public Julia(Context context) {
        super(context);
        paint = new Paint();
        paint.setColor(Color.WHITE);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        if (bitmap == null) {
            int ancho = canvas.getWidth();
            int alto = canvas.getHeight();
            bitmap = Bitmap.createBitmap(ancho, alto, Bitmap.Config.ARGB_8888);
            drawJulia(bitmap, ancho, alto);
        }

        canvas.drawBitmap(bitmap, 0, 0, paint);
    }

    private void drawJulia(Bitmap bitmap, int ancho, int alto) {
        double minX = -1.5;
        double maxX = 1.5;
        double minY = -1.5;
        double maxY = 1.5;

        for (int y = 0; y < alto; y++) {
            for (int x = 0; x < ancho; x++) {
                double a = map(x, 0, ancho, minX, maxX);
                double b = map(y, 0, alto, minY, maxY);

                int iteraciones = julia(a, b, 100);
                int color = Color.HSVToColor(new float[]{iteraciones % 360, 1, iteraciones > 0 ? 1 : 0});
                bitmap.setPixel(x, y, color);
            }
        }
    }

    private double map(int value, int start1, int stop1, double start2, double stop2) {
        return start2 + (stop2 - start2) * ((double) value - start1) / (stop1 - start1);
    }

    private int julia(double a, double b, int maxIteraciones) {
        double x = a;
        double y = b;
        int n;

        for (n = 0; n < maxIteraciones; n++) {
            double xTemp = x * x - y * y + C_REAL;
            y = 2 * x * y + C_IMAG;
            x = xTemp;

            if (x * x + y * y > 4) {
                return n;
            }
        }

        return maxIteraciones;
    }
}
