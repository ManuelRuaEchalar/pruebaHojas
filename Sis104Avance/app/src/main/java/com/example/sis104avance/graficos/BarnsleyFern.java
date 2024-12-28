package com.example.sis104avance.graficos;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.Random;

public class BarnsleyFern extends View {

    private static final int NUM_POINTS = 100000;
    private final float[] points;
    private final Random random = new Random();
    private final Paint paint = new Paint();

    public BarnsleyFern(Context context) {
        super(context);
        points = new float[NUM_POINTS * 2];
        generateFern();
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(1);
    }

    private void generateFern() {
        float x = 0, y = 0;
        for (int i = 0; i < NUM_POINTS; i++) {
            float nextX, nextY;
            float r = random.nextFloat();
            if (r < 0.01f) {
                nextX = 0;
                nextY = 0.16f * y;
            } else if (r < 0.86f) {
                nextX = 0.85f * x + 0.04f * y;
                nextY = -0.04f * x + 0.85f * y + 1.6f;
            } else if (r < 0.93f) {
                nextX = 0.20f * x - 0.26f * y;
                nextY = 0.23f * x + 0.22f * y + 1.6f;
            } else {
                nextX = -0.15f * x + 0.28f * y;
                nextY = 0.26f * x + 0.24f * y + 0.44f;
            }
            x = nextX;
            y = nextY;
            points[i * 2] = x;
            points[i * 2 + 1] = y;
        }
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);

        int width = getWidth();
        int height = getHeight();
        float scale = Math.min(width / 6f, height / 10f);
        canvas.translate(width / 2f, height);
        canvas.scale(scale, -scale);

        for (int i = 0; i < NUM_POINTS; i++) {
            float x = points[i * 2];
            float y = points[i * 2 + 1];
            canvas.drawPoint(x, y, paint);
        }
    }
}