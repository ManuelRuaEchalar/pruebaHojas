package com.example.eco.ui.games.minigamedos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Paint;
import android.os.Handler;
import java.util.ArrayList;
import java.util.Random;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.NonNull;

import com.example.eco.R;


public class GameView extends View {

    Bitmap background, ground, contenedor;
    Rect rectBackground, rectGround;
    Context context;
    Handler handler;
    final long UPDATE_MILLIS = 17;
    Runnable runnable;
    Paint textPaint = new Paint();
    Paint healthPaint = new Paint();
    float TEXT_SIZE = 80;
    int points = 0;
    int life = 3;
    static int dWidth, dHeight;
    Random random;
    float contenedorX, contenedorY;
    float oldX;
    float oldContenedorX;
    ArrayList<Spike> spikes;
    ArrayList<Explosion> explosions;
    Bitmap scaledBackground;
    Bitmap scaledGround;

    public GameView(Context context) {
        super(context);
        this.context = context;
        background = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        ground = BitmapFactory.decodeResource(getResources(), R.drawable.ground);

        // Seleccionar imagen del contenedor según el número aleatorio
        switch (MiniGameDosActivity.randomImageIndex) {
            case 1:
                contenedor = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.cont1), 100, 100, false);
                break;
            case 2:
                contenedor = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.cont2), 100, 100, false);
                break;
            case 3:
                contenedor = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.cont3), 100, 100, false);
                break;
            case 4:
                contenedor = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.cont4), 100, 100, false);
                break;
            case 5:
                contenedor = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.cont5), 100, 100, false);
                break;
            case 6:
                contenedor = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.cont6), 100, 100, false);
                break;
        }

        // Obtener dimensiones de la pantalla
        Display display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        dWidth = size.x;
        dHeight = size.y;

        rectBackground = new Rect(0, 0, dWidth, dHeight);
        rectGround = new Rect(0, dHeight - ground.getHeight(), dWidth, dHeight);

        handler = new Handler();
        runnable = () -> invalidate();

        textPaint.setTextSize(TEXT_SIZE);
        textPaint.setColor(Color.rgb(0, 0, 0));
        textPaint.setTextAlign(Paint.Align.LEFT);
        healthPaint.setColor(Color.GREEN);
        random = new Random();

        contenedorX = dWidth / 2 - contenedor.getWidth() / 2;
        contenedorY = dHeight - ground.getHeight() - contenedor.getHeight();

        spikes = new ArrayList<>();
        explosions = new ArrayList<>();

        // Crear los spikes (dañinos y de recompensa)
        for (int i = 0; i < 3; i++) {
            spikes.add(new Spike(context, false));  // Spikes dañinos
            spikes.add(new Spike(context, true));   // Spikes de recompensa
        }

        scaledBackground = Bitmap.createScaledBitmap(background, dWidth, dHeight, false);
        scaledGround = Bitmap.createScaledBitmap(ground, dWidth, ground.getHeight(), false);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(scaledBackground, null, rectBackground, null);
        canvas.drawBitmap(scaledGround, null, rectGround, null);

        // Dibujar el contenedor
        canvas.drawBitmap(contenedor, contenedorX, contenedorY, null);

        // Dibujar y actualizar los spikes
        for (int i = 0; i < spikes.size(); i++) {
            Spike spike = spikes.get(i);
            canvas.drawBitmap(spike.getSpike(spike.spikeFrame), spike.spikeX, spike.spikeY, null);
            spike.spikeFrame = (spike.spikeFrame + 1) % 3;
            spike.spikeY += spike.spikeVelocity;

            if (spike.spikeY + spike.getSpikeHeight() > dHeight - ground.getHeight()) {
                if (spike.isRewardSpike) {
                    points -= 150;  // Penalización por dejar caer un spike de recompensa
                }
                Explosion explosion = new Explosion(context);
                explosion.explosionX = (int) spike.spikeX;
                explosion.explosionY = (int) spike.spikeY;
                explosions.add(explosion);
                spike.resetPosition(); // Reiniciar la posición del spike
            }

            // Detección de colisión con el contenedor
            if (spike.spikeX + spike.getSpikeWidth() >= contenedorX &&
                    spike.spikeX <= contenedorX + contenedor.getWidth() &&
                    spike.spikeY + spike.getSpikeHeight() >= contenedorY &&
                    spike.spikeY <= contenedorY + contenedor.getHeight()) {

                if (spike.isRewardSpike) {
                    points += 100;  // Ganar puntos
                } else {
                    life--;  // Perder vida
                }
                spike.resetPosition();

// Fin del juego si la vida es 0 (perdió)
                if (life == 0) {
                    Intent intent = new Intent(context, GameOverActivity.class);
                    intent.putExtra("points", points);
                    intent.putExtra("status", "lost"); // Indicamos que perdió
                    context.startActivity(intent);
                    ((Activity) context).finish();
                }

// Comprobar si ha alcanzado 3000 puntos (ganó)
                if (points >= 500) {
                    Intent intent = new Intent(context, GameOverActivity.class);
                    intent.putExtra("points", points);
                    intent.putExtra("status", "won"); // Indicamos que ganó
                    context.startActivity(intent);
                    ((Activity) context).finish();
                }

            }
        }

        // Dibujar explosiones
        for (int i = explosions.size() - 1; i >= 0; i--) {
            Explosion explosion = explosions.get(i);
            canvas.drawBitmap(explosion.getExplosion(explosion.explosionFrame), explosion.explosionX, explosion.explosionY, null);
            explosion.explosionFrame++;
            if (explosion.explosionFrame > 3) {
                explosions.remove(i);  // Eliminar la explosión después de la animación
            }
        }

        // Dibujar barra de salud
        if (life == 2) healthPaint.setColor(Color.YELLOW);
        if (life == 1) healthPaint.setColor(Color.RED);
        canvas.drawRect(dWidth - 200, 30, dWidth - 200 + 60 * life, 80, healthPaint);

        // Dibujar puntos
        canvas.drawText("PUNTOS: " + points, 20, TEXT_SIZE, textPaint);

        handler.postDelayed(runnable, UPDATE_MILLIS);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        if (event.getY() >= contenedorY) {
            int action = event.getAction();
            if (action == MotionEvent.ACTION_DOWN) {
                oldX = touchX;
                oldContenedorX = contenedorX;
            }

            if (action == MotionEvent.ACTION_MOVE) {
                float shift = oldX - touchX;
                float newContenedorX = oldContenedorX - shift;
                contenedorX = Math.max(0, Math.min(newContenedorX, dWidth - contenedor.getWidth()));
            }
        }
        return true;
    }
}

