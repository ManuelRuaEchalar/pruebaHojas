package com.example.sis104avance.graficos;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

public class Grafico2d extends View {

    private static final String TAG = "Grafico2d";
    public Grafico2d(Context context) {
        super(context);
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

        float limiteInferiorX = -20;
        float limiteSuperiorX = 20;

        float limiteInferiorY = -20;
        float limiteSuperiorY = 20;

        paint.setColor(Color.YELLOW);

        for(float x=limiteInferiorX; x<=limiteSuperiorX; x+=0.01){
            double y = fxsin(x);
            Log.d(TAG, " datos: x: " + x + " y:" + y);
            double xt = (x-limiteInferiorX)/(limiteSuperiorX-limiteInferiorX)*ancho;
            double yt = alto - (y-limiteInferiorY)/(limiteSuperiorY-limiteInferiorY)*alto;
            Log.d(TAG, " datos transformados: xt: " + xt + " yt:" + yt);
            canvas.drawCircle((float) xt, (float) yt, 3, paint);

        }

    }

    private double fx(float x){
        return x*x;
    }

    private double fxsin(float x){
        return x*Math.sin(x);
    }
}
