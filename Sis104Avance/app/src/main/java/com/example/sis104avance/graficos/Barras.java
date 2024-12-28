package com.example.sis104avance.graficos;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

public class Barras extends View {

    int a, b, c;

    private static final String TAG = "Barras";
    public Barras(Context context, int a, int b, int c) {
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

        canvas.drawLine(0, alto/2, ancho, alto/2, paint);
        canvas.drawLine(ancho/2, 0, ancho/2, alto, paint);

       if (a>100){
           a = a * alto/5000;
       } else {a = a;}

        if (b>100){
            b = b * alto/5000;
        } else {b = b;}

        if (c>100){
            c = c * alto/5000;
        } else {c = c;}


        paint.setColor(Color.CYAN);
        String barra1 = "A: " + a + "%";;
        canvas.drawText(barra1, 200, (float) (alto/2)-a*10, paint);
        paint.setColor(Color.YELLOW);
        canvas.drawRect(200, (float) (alto/2)-a*10, 250, (float) alto/2, paint);

        paint.setColor(Color.CYAN);
        String barra2 = "B: " + b + "%";;
        canvas.drawText(barra2, 400, (float) (alto/2)-b*10, paint);
        paint.setColor(Color.YELLOW);
        canvas.drawRect(400, (float) (alto/2)-b*10, 450, (float) alto/2, paint);

        paint.setColor(Color.CYAN);
        String barra3 = "C: " + c + "%";
        canvas.drawText(barra3, 600, (float) (alto/2)-c*10, paint);
        paint.setColor(Color.YELLOW);
        canvas.drawRect(600, (float) (alto/2)-c*10, 650, (float) alto/2, paint);

    }


}



