package com.example.sis104avance.graficos;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;


public class Torta extends View {

    int a, b, c;

    private static final String TAG = "Barras";
    public Torta(Context context, int a, int b, int c) {
        super(context);
        this.a = a;
        this.b = b;
        this.c = c;

    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {

        super.onDraw(canvas);

        canvas.drawColor(Color.BLACK);
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);

        int ancho = canvas.getWidth();
        int alto = canvas.getHeight();
        Log.d(TAG, "onDraw: ancho: " + ancho + " alto: " + alto);


        if (a>100){
            a = a * alto/5000;
        } else {a = a;}

        if (b>100){
            b = b * alto/5000;
        } else {b = b;}

        if (c>100){
            c = c * alto/5000;
        } else {c = c;}


        canvas.drawArc(0, 0, ancho/2, alto/2, 0, a, true, paint);
        canvas.drawArc(0, 0, ancho/2, alto/2, a, b, true, paint);
        canvas.drawArc(0, 0, ancho/2, alto/2, a+b, c, true, paint);

    }


}
