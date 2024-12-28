package com.example.eco.ui.games.minigametres;

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
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Build;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.NonNull;

import com.example.eco.R;
import com.example.eco.ui.games.IObject;
import com.example.eco.ui.games.OkAnimation;
import com.example.eco.ui.games.BlackObject;
import com.example.eco.ui.games.BlueObject;
import com.example.eco.ui.games.GreenObject;
import com.example.eco.ui.games.GrayObject;
import com.example.eco.ui.games.RedObject;
import com.example.eco.ui.games.YellowObject;

import java.util.ArrayList;
import java.util.Random;

public class GameView extends View {

    Bitmap background;
    Rect rectBackground;
    Context context;
    Handler handler;
    final long UPDATE_MILLIS = 30;
    Runnable runnable;
    Paint textPaint = new Paint();
    Paint healthPaint = new Paint();
    Paint gridPaint = new Paint();
    float TEXT_SIZE = 80;
    int points = 0;
    int life = 3;
    static int dWidth, dHeight;
    Bitmap scaledBackground;
    ArrayList<IObject> objects;

    // Grid variables
    int gridTop, gridMiddle, gridBottom;
    int gridLeft, gridCenter, gridRight;
    Rect[] gridRects = new Rect[6];

    // Drag and drop variables
    private IObject draggedObject;
    private float dragOffsetX, dragOffsetY;
    private float originalX, originalY;

    int objectWidth = 150;
    int objectHeight = 150;
    int safeTopMargin = 100;

    Random random;

    SoundPool soundPool;
    int lifeLossSoundId;
    int correctSoundId;
    int moveSoundId;
    int countRepeatedFrames;
    int countWrongRepeatedFrames;

    OkAnimation okAnimation;
    WrongAnimation wrongAnimation;


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
        moveSoundId = soundPool.load(context, R.raw.mover, 1);
        countRepeatedFrames = 0;
        countWrongRepeatedFrames = 0;
        this.context = context;
        background = BitmapFactory.decodeResource(getResources(), R.drawable.background_game_1);
        random = new Random();
        Display display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        dWidth = size.x;
        dHeight = size.y;

        rectBackground = new Rect(0, 0, dWidth, dHeight);

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };

        textPaint.setTextSize(TEXT_SIZE);
        textPaint.setColor(Color.GREEN);
        textPaint.setTextAlign(Paint.Align.LEFT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            textPaint.setTypeface(getResources().getFont(R.font.kenney_blocks));
        }

        healthPaint.setColor(Color.GREEN);

        gridPaint.setColor(Color.WHITE);
        gridPaint.setStrokeWidth(5);

        scaledBackground = Bitmap.createScaledBitmap(background, dWidth, dHeight, false);

        initializeGridPositions();
        createRandomObjects(20);





    }

    private void initializeGridPositions() {
        gridTop = dHeight - dHeight / 3;
        gridMiddle = dHeight - 35 - (dHeight / 3) / 2;
        gridBottom = dHeight;
        gridLeft = 0;
        gridCenter = dWidth / 3;
        gridRight = dWidth * 2 / 3;

        // Initialize the 6 rectangle positions
        gridRects[0] = new Rect(gridLeft, gridTop, gridCenter, gridMiddle);
        gridRects[1] = new Rect(gridCenter, gridTop, gridRight, gridMiddle);
        gridRects[2] = new Rect(gridRight, gridTop, dWidth, gridMiddle);
        gridRects[3] = new Rect(gridLeft, gridMiddle, gridCenter, gridBottom);
        gridRects[4] = new Rect(gridCenter, gridMiddle, gridRight, gridBottom);
        gridRects[5] = new Rect(gridRight, gridMiddle, dWidth, gridBottom);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(scaledBackground, null, rectBackground, null);

        // Draw grid
        canvas.drawLine(0, gridTop, dWidth, gridTop, gridPaint);
        canvas.drawLine(0, gridMiddle, dWidth, gridMiddle, gridPaint);
        canvas.drawLine(gridCenter, gridTop, gridCenter, gridBottom, gridPaint);
        canvas.drawLine(gridRight, gridTop, gridRight, gridBottom, gridPaint);

        // Draw objects
        for (IObject object : objects) {
            canvas.drawBitmap(object.getObject(), object.getObjectX(), object.getObjectY(), null);
        }
        if (draggedObject != null) {
            canvas.drawBitmap(draggedObject.getObject(), draggedObject.getObjectX(), draggedObject.getObjectY(), null);
        }
        // Draw health bar
        if (life == 2) {
            healthPaint.setColor(Color.YELLOW);
        } else if (life == 1) {
            healthPaint.setColor(Color.RED);
        } else {
            healthPaint.setColor(Color.GREEN);
        }
        canvas.drawRect(dWidth - 200, 30, dWidth - 200 + 60 * life, 80, healthPaint);

        // Draw points
        canvas.drawText("Points: " + points, 20, TEXT_SIZE, textPaint);

        if(okAnimation!=null){
            canvas.drawBitmap(okAnimation.getOkCheck(okAnimation.okFrame),okAnimation.getOkPosX(),okAnimation.getOkPosY(),null);

            okAnimation.okFrame++;
            if(okAnimation.okFrame>=6){
                okAnimation.okFrame=5;
                countRepeatedFrames++;
                if(countRepeatedFrames>=25){
                    okAnimation=null;
                    countRepeatedFrames=0;

                }

            }
        }
        else{
            countRepeatedFrames=0;
        }
        if(wrongAnimation!=null){

            canvas.drawBitmap(wrongAnimation.getWrongCheck(wrongAnimation.wrongFrame),wrongAnimation.getWrongPosX(),wrongAnimation.getWrongPosY(),null);

            wrongAnimation.wrongFrame++;
            if(wrongAnimation.wrongFrame>=6){
                wrongAnimation.wrongFrame=5;
                countWrongRepeatedFrames++;
                if(countWrongRepeatedFrames>=25){
                    wrongAnimation=null;
                    countWrongRepeatedFrames=0;

                }
            }
        }
        else{
            countWrongRepeatedFrames=0;
        }

        handler.postDelayed(runnable, UPDATE_MILLIS);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // Check if touch is on an object
                for (IObject object : objects) {
                    if (isTouchOnObject(touchX, touchY, object)) {
                        soundPool.play(moveSoundId, 1, 1, 1, 0, 1);
                        draggedObject = object;
                        dragOffsetX = touchX - object.getObjectX();
                        dragOffsetY = touchY - object.getObjectY();
                        originalX = object.getObjectX();
                        originalY = object.getObjectY();
                        objects.remove(object);
                        invalidate();
                        return true;
                    }
                }
                break;

            case MotionEvent.ACTION_MOVE:
                if (draggedObject != null) {
                    draggedObject.setObjectX((int) (touchX - dragOffsetX));
                    draggedObject.setObjectY((int) (touchY - dragOffsetY));
                    invalidate();
                    return true;
                }
                break;

            case MotionEvent.ACTION_UP:
                if (draggedObject != null) {
                    boolean dropped = false;
                    for (int i = 0; i < gridRects.length; i++) {
                        if (gridRects[i].contains((int)touchX, (int)touchY)) {
                            handleObjectDrop(i);
                            dropped = true;
                            break;
                        }
                    }

                    if (!dropped || draggedObject.getColor() != findRectIndex(touchX, touchY)) {
                        // If object wasn't dropped in any rectangle or was dropped incorrectly,
                        // return it to its original position
                        draggedObject.setObjectX((int) originalX);
                        draggedObject.setObjectY((int) originalY);
                        objects.add(draggedObject);
                    }

                    draggedObject = null;
                    invalidate();
                    return true;
                }
                break;
        }

        return true;
    }
    private int findRectIndex(float x, float y) {
        for (int i = 0; i < gridRects.length; i++) {
            if (gridRects[i].contains((int)x, (int)y)) {
                return i;
            }
        }
        return -1; // Not found in any rectangle
    }

    private void handleObjectDrop(int rectIndex) {
        if (draggedObject.getColor() == rectIndex) {
            //new okAnimation on the dragged object coordinates
            okAnimation=new OkAnimation(context,draggedObject.getObjectX(),draggedObject.getObjectY());

            // Correct drop
            points += 20;




            soundPool.play(correctSoundId, 1, 1, 1, 0, 1);
            // Object is removed by not adding it back to the objects list
            if (objects.isEmpty()) {
                Intent intent = new Intent(context, GameOverActivity.class);
                intent.putExtra("points", points);
                intent.putExtra("won",true);
                context.startActivity(intent);
                ((Activity) context).finish();
            }
        } else {
            // Incorrect drop
            life--;
            //create a wrong animation
            wrongAnimation=new WrongAnimation(context,draggedObject.getObjectX(),draggedObject.getObjectY());

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
            // Object will be returned to its original position in the onTouchEvent method
        }
    }

    private boolean isTouchOnObject(float touchX, float touchY, IObject object) {
        return touchX >= object.getObjectX() && touchX < object.getObjectX() + object.getObject().getWidth() &&
                touchY >= object.getObjectY() && touchY < object.getObjectY() + object.getObject().getHeight();
    }


    private void createRandomObjects(int numberOfObjects) {
        objects = new ArrayList<>();

        for (int i = 0; i < numberOfObjects; i++) {
            IObject newObject;
            boolean isValidPosition;

            // Try finding a valid position for each object
            do {
                isValidPosition = true;

                // Generate random x and y positions within allowed range
                int randomX = (int) (Math.random() * (dWidth - objectWidth));
                int randomY = (int) (Math.random() * (gridTop - safeTopMargin - objectHeight)) + safeTopMargin;

                // Create the new object
                newObject = getRandomObject(randomX, randomY);

                // Check if the new object overlaps with any existing object
                for (IObject existingObject : objects) {
                    if (isOverlapping(existingObject, newObject)) {
                        isValidPosition = false;
                        break;
                    }
                }
            } while (!isValidPosition);

            // Add the new object to the list if its position is valid
            objects.add(newObject);
        }
    }

    // Method to check if two objects overlap
    private boolean isOverlapping(IObject object1, IObject object2) {
        Rect rect1 = new Rect(object1.getObjectX(), object1.getObjectY(), object1.getObjectX() + objectWidth, object1.getObjectY() + objectHeight);
        Rect rect2 = new Rect(object2.getObjectX(), object2.getObjectY(), object2.getObjectX() + objectWidth, object2.getObjectY() + objectHeight);
        return Rect.intersects(rect1, rect2);
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