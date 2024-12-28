package com.example.eco.ui.games;

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
import android.os.Build;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.Random;
import android.media.AudioAttributes;
import android.media.SoundPool;

import com.example.eco.R;

public class GameView extends View {

    Bitmap background, ground, bunny;
    Rect rectBackground, rectGround;
    Context context;
    Handler handler;
    final long UPDATE_MILLIS = 30;
    Runnable runnable;
    Paint textPaint = new Paint();
    Paint healthPaint = new Paint();
    float TEXT_SIZE = 80;
    int points = 0;
    int life = 3;
    static int dWidth, dHeight;
    Random random;
    float bunnyX, bunnyY;
    ArrayList<IObject> objects;
    Spike spike;
    boolean spikeActive;
    ArrayList<Explosion> explosions;
    Bitmap scaledBackground;
    Bitmap scaledGround;
    boolean firstDraw = true;
    int colorBunny;
    private int groundHeight;
    private int groundTopY;

    SoundPool soundPool;
    int lifeLossSoundId;
    int correctSoundId;
    int shootSoundId;
    int moveSoundId;


    public GameView(Context context) {

        super(context);

        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();


        soundPool = new SoundPool.Builder()
                .setMaxStreams(1)  // Maximum number of simultaneous streams
                .setAudioAttributes(audioAttributes)
                .build();

        lifeLossSoundId = soundPool.load(context, R.raw.incorrecto, 1);
        correctSoundId = soundPool.load(context, R.raw.correcto, 1);
        shootSoundId = soundPool.load(context, R.raw.disparar, 1);
        moveSoundId = soundPool.load(context, R.raw.mover, 1);

        colorBunny =0;
        this.context = context;
        background = BitmapFactory.decodeResource(getResources(), R.drawable.background_black_full);
        ground = BitmapFactory.decodeResource(getResources(), R.drawable.buttons);
        int bunnyWidth = 150;
        int bunnyHeight = 250;


        bunny = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.tanque_amarillo), bunnyWidth, bunnyHeight, false);

        Display display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        dWidth = size.x;
        dHeight = size.y;

        rectBackground = new Rect(0, 0, dWidth, dHeight);
//        rectGround = new Rect(0, dHeight - ground.getHeight(), dWidth, dHeight);

        groundHeight = ground.getHeight() / 2;
        groundTopY = dHeight - 100 - groundHeight;
        rectGround = new Rect(0, groundTopY, dWidth, dHeight - 100);

        bunnyY = groundTopY - bunny.getHeight();
//        int groundHeight = ground.getHeight() / 2;
//        rectGround = new Rect(0, dHeight-100 - groundHeight, dWidth, dHeight-100);

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };

        textPaint.setTextSize(TEXT_SIZE);
//        textPaint.setColor(Color.rgb(255, 165, 0));
        textPaint.setColor(Color.WHITE);
        textPaint.setTextAlign(Paint.Align.LEFT);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            textPaint.setTypeface(getResources().getFont(R.font.kenney_blocks));
        }

        healthPaint.setColor(Color.GREEN);
        random = new Random();

        bunnyX = dWidth / 2 - bunny.getWidth() / 2;
//        bunnyY = dHeight - rectGround.height()-100 - bunny.getHeight();

        explosions = new ArrayList<>();
        objects = new ArrayList<>();

        // Crear y agregar GrayObjects en posiciones fijas
        for (int i = 0; i < 20; i++) {
            int objX = (dWidth / 5) * (i % 5) + (dWidth / 10) - 75; // Centra el objeto en las columnas
            int objY;
            if (i < 5) {
                objY = 200; // Primera fila
                objects.add(getRandomObject(objX,objY));
            } else if (i < 10) {
                objY = 400; // Segunda fila
                objects.add(getRandomObject(objX,objY));
            } else if (i<15) {
                objY = 600; // Tercera fila
                objects.add(getRandomObject(objX,objY));
            }
            else{
                objY = 800; // Tercera fila
                objects.add(getRandomObject(objX,objY));
            }

        }
//        for (int i = 0; i < 15; i++) {
//            int objX = (dWidth / 5) * (i % 5) + (dWidth / 10) - 75; // Centra el objeto en las columnas
//            int objY;
//
//            if (i < 5) {
//                objY = 200; // Primera fila
//                objects.add(new GrayObject(context, objX, objY));
//            } else if (i < 10) {
//                objY = 400; // Segunda fila
//                objects.add(new GreenObject(context, objX, objY));
//            } else {
//                objY = 600; // Tercera fila
//                objects.add(new GrayObject(context, objX, objY));
//            }
//        }

        spike = new Spike(context);
        spikeActive = false;

        scaledBackground = Bitmap.createScaledBitmap(background, dWidth, dHeight, false);
//        scaledGround = Bitmap.createScaledBitmap(ground, dWidth, ground.getHeight(), false);
        scaledGround = Bitmap.createScaledBitmap(ground, dWidth, groundHeight, false);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(scaledBackground, null, rectBackground, null);
        canvas.drawBitmap(scaledGround, null, rectGround, null);
        canvas.drawBitmap(bunny, bunnyX, bunnyY, null);

        // Dibujar los objetos
        drawObjects(canvas);

        if (spikeActive) {
            canvas.drawBitmap(spike.getSpike(), spike.spikeX, spike.spikeY, null);

            spike.spikeY -= spike.spikeVelocity;

            if (spike.spikeY < 0) {
                Explosion explosion = new Explosion(context);
                explosion.explosionX = spike.spikeX;
                explosion.explosionY = spike.spikeY;
                explosions.add(explosion);
                spikeActive = false;
                spike.resetPosition();
            }

            // Colisión con objetos
            for (int i = 0; i < objects.size(); i++) {
                IObject obj = objects.get(i);

                if (spike.spikeX < obj.getObjectX() + obj.getObjectWidth() &&
                        spike.spikeX + spike.getSpikeWidth() > obj.getObjectX() &&
                        spike.spikeY < obj.getObjectY() + obj.getObjectHeight() &&
                        spike.spikeY + spike.getSpikeHeight() > obj.getObjectY()) {

                    Explosion explosion = new Explosion(context);
                    explosion.explosionX = obj.getObjectX();
                    explosion.explosionY = obj.getObjectY();
                    explosions.add(explosion);
                    if(colorBunny == obj.getColor()){
                        points += 20;
                        soundPool.play(correctSoundId, 1, 1, 1, 0, 1);

                    }
                    else{
                        life--;
//                        soundPool.play(lifeLossSoundId, 1, 1, 1, 0, 1);
                        soundPool.play(lifeLossSoundId, 1, 1, 1, 0, 1);
                        if (points <= 10) {
                            points= 0;
                        }
                        else{
                            points -= 10;
                        }

                        if (life == 0) {
                            Intent intent = new Intent(context, GameOverActivity.class);
                            intent.putExtra("points", points);
                            intent.putExtra("won",false);
                            context.startActivity(intent);
                            ((Activity) context).finish();
                        }
                    }


                    objects.remove(i);
                    if (objects.isEmpty()) {
                        if(life <= 0){
                            Intent intent = new Intent(context, GameOverActivity.class);
                            intent.putExtra("points", points);
                            intent.putExtra("won",false);
                            context.startActivity(intent);
                            ((Activity) context).finish();
                        }
                        else{
                            Intent intent = new Intent(context, GameOverActivity.class);
                            intent.putExtra("points", points);
                            intent.putExtra("won",true);
                            context.startActivity(intent);
                            ((Activity) context).finish();
                        }

                    }
                    spikeActive = false;
                    spike.resetPosition();
                    break;
                }
            }
        }

        // Dibujar explosiones
        for (int i = 0; i < explosions.size(); i++) {
            canvas.drawBitmap(explosions.get(i).getExplosion(explosions.get(i).explosionFrame), explosions.get(i).explosionX,
                    explosions.get(i).explosionY, null);
            explosions.get(i).explosionFrame++;
            if (explosions.get(i).explosionFrame > 3) {
                explosions.remove(i);
            }
        }

        // Mostrar vida y puntos
        if (life == 2) {
            healthPaint.setColor(Color.YELLOW);
        }
        if (life == 1) {
            healthPaint.setColor(Color.RED);
        }

        canvas.drawRect(dWidth - 200, 30, dWidth - 200 + 60 * life, 80, healthPaint);
        canvas.drawText("Points: " + points, 20, TEXT_SIZE, textPaint);

        handler.postDelayed(runnable, UPDATE_MILLIS);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();
        int[] bunnyPositions = new int[5];
        for (int i = 0; i < 5; i++) {
            bunnyPositions[i] = i * (dWidth / 5) + (dWidth / 10) - bunny.getWidth() / 2;
        }

        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {

            if(touchY>=groundTopY+ (float) groundHeight /2){
                if (touchX < (float) dWidth / 3) {
                    moveBunnyLeft(bunnyPositions);
                    soundPool.play(moveSoundId, 1, 1, 0, 0, 1);
                } else if (touchX > (float) (2 * dWidth) / 3) {
                    moveBunnyRight(bunnyPositions);
                    soundPool.play(moveSoundId, 1, 1, 0, 0, 1);
                } else {
                    if (!spikeActive) {
                        spike.spikeX = (int) (bunnyX + (float) bunny.getWidth() / 2 - (float) spike.getSpikeWidth() / 2);
                        spike.spikeY = (int) bunnyY - 80;
                        spikeActive = true;
                        soundPool.play(shootSoundId, 1, 1, 1, 0, 1);

                    }

                }

            }

//            if (touchX < dWidth / 3 && touchY > dHeight - (float) rectGround.height() /2 ) {
//                moveBunnyLeft(bunnyPositions);
//            } else if (touchX > 2 * dWidth / 3  && touchY > dHeight - (float) rectGround.height() /2 ) {
//                moveBunnyRight(bunnyPositions);
//            } else if(touchY > dHeight - (float) rectGround.height() /2 ){
//                if (!spikeActive) {
//                    spike.spikeX = (int) (bunnyX + (float) bunny.getWidth() / 2 - (float) spike.getSpikeWidth() / 2);
//                    spike.spikeY = (int) bunnyY-80;
//                    spikeActive = true;
//                }
//            }
//            else if(touchY > dHeight - rectGround.height() && touchY < dHeight - (float) rectGround.height() / 2) {
//                colorBunny++;
//                if (colorBunny > 5) colorBunny = 0; // Reset colorBunny after the last color
//                updateBunnyBitmap();  // Update the bunny's bitmap after changing the color
//            }
            else if(touchY >groundTopY ) {
                //divide in 6 parts screen width to compare to assign a static value to color bunny
                int[] colorPositions = new int[6];
                for (int i = 0; i < 6; i++) {
                    colorPositions[i] = i * (dWidth / 6);
                }
                if(touchX>colorPositions[0] && touchX<colorPositions[1]){
                    colorBunny = 0;
                }
                else if(touchX>colorPositions[1] && touchX<colorPositions[2]){
                    colorBunny = 1;
                }
                else if(touchX>colorPositions[2] && touchX<colorPositions[3]){
                    colorBunny = 2;
                }
                else if(touchX>colorPositions[3] && touchX<colorPositions[4]){
                    colorBunny = 3;
                }
                else if(touchX>colorPositions[4] && touchX<colorPositions[5]){
                    colorBunny = 4;
                }
                else{
                    colorBunny = 5;}

//                colorBunny++;
//                if (colorBunny > 5) colorBunny = 0;
                updateBunnyBitmap();
            }
        }

        return true;
    }

    private void moveBunnyLeft(int[] bunnyPositions) {
        for (int i = 1; i < bunnyPositions.length; i++) {
            if (bunnyX == bunnyPositions[i]) {
                bunnyX = bunnyPositions[i - 1];
                break;
            }
        }
    }

    private void moveBunnyRight(int[] bunnyPositions) {
        for (int i = 0; i < bunnyPositions.length - 1; i++) {
            if (bunnyX == bunnyPositions[i]) {
                bunnyX = bunnyPositions[i + 1];
                break;
            }
        }
    }

    public void drawObjects(Canvas canvas) {
        for (IObject object : objects) {
            canvas.drawBitmap(object.getObject(), object.getObjectX(), object.getObjectY(), null);
        }
    }

    private void updateBunnyBitmap() {
        int bunnyWidth = 150;
        int bunnyHeight = 250;
        switch (colorBunny) {
            case 0:
                bunny = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.tanque_amarillo), bunnyWidth, bunnyHeight, false);
                break;
            case 1:
                bunny = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.tanque_rojo), bunnyWidth, bunnyHeight, false);
                break;
            case 2:
                bunny = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.tanque_plomo), bunnyWidth, bunnyHeight, false);
                break;
            case 3:
                bunny = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.tanque_negro), bunnyWidth, bunnyHeight, false);
                break;
            case 4:
                bunny = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.tanque_verde), bunnyWidth, bunnyHeight, false);
                break;
            case 5:
                bunny = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.tanque_azul), bunnyWidth, bunnyHeight, false);
                break;
        }
    }

    private IObject getRandomObject(int x, int y) {
        int randomNum = random.nextInt(6);
        switch (randomNum){
            case 0:
                return new GrayObject(context, x, y);
            case 1:
                return new GreenObject(context, x, y);
            case 2:
                return new BlueObject(context, x, y);
            case 3:
                return new RedObject(context, x, y);
            case 4:
                return new YellowObject(context, x, y);
            case 5:
                return new BlackObject(context, x, y);

            default:
                return new RedObject(context,x,y);
        }

    }
}
