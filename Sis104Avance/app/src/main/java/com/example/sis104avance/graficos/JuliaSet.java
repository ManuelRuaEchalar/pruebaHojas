package com.example.sis104avance.graficos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import androidx.annotation.NonNull;

public class JuliaSet extends View {

    private Bitmap bitmap;
    private final int MAX_ITERATIONS = 100;
    private final double ZOOM = 1;
    private final double CX = -0.7; // Parte real del número complejo C
    private final double CY = 0.27015; // Parte imaginaria del número complejo C

    public JuliaSet(Context context) {
        super(context);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (bitmap == null || bitmap.getWidth() != w || bitmap.getHeight() != h) {
            bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.RGB_565);
        }
        drawJuliaSet();
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, 0, 0, null);
        }
    }

    private void drawJuliaSet() {
        if (bitmap == null) return;

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                double zx = 1.5 * (x - width / 2) / (0.5 * ZOOM * width);
                double zy = (y - height / 2) / (0.5 * ZOOM * height);

                int iteration = 0;
                while (zx * zx + zy * zy < 4 && iteration < MAX_ITERATIONS) {
                    double tmp = zx * zx - zy * zy + CX;
                    zy = 2.0 * zx * zy + CY;
                    zx = tmp;
                    iteration++;
                }

                int color;
                if (iteration < MAX_ITERATIONS) {
                    float hue = (float) iteration / MAX_ITERATIONS;
                    color = Color.HSVToColor(new float[]{hue * 360, 1, 1});
                } else {
                    color = Color.BLACK;
                }

                bitmap.setPixel(x, y, color);
            }
        }

        invalidate();
    }
}