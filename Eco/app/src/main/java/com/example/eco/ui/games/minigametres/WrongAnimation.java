package com.example.eco.ui.games.minigametres;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.eco.R;

public class WrongAnimation {
    Bitmap wrongCheck[] = new Bitmap[10];
    int wrongFrame = 0;


    int frameWidth = 150;
    int frameHeight = 150;

    int posX, posY;
    public WrongAnimation(Context context, int x, int y) {
        wrongCheck[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.wrong_1), frameWidth, frameHeight, false);
        wrongCheck[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.wrong_2), frameWidth, frameHeight, false);
        wrongCheck[2] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.wrong_3), frameWidth, frameHeight, false);
        wrongCheck[3] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.wrong_4), frameWidth, frameHeight, false);
        wrongCheck[4] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.wrong_5), frameWidth, frameHeight, false);
        wrongCheck[5] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.wrong_6), frameWidth, frameHeight, false);


        posX = x;
        posY = y;

    }

    public Bitmap getWrongCheck(int frame) {
        return wrongCheck[frame];
    }

    public int getWrongPosX(){
        return posX;
    }

    public int getWrongPosY(){
        return posY;
    }

}
