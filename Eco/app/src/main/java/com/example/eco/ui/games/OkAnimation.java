package com.example.eco.ui.games;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.eco.R;

public class OkAnimation {
    Bitmap okCheck[] = new Bitmap[10];
    public int okFrame = 0;


    int frameWidth = 150;
    int frameHeight = 150;

    int posX, posY;
    public OkAnimation(Context context, int x, int y) {
        okCheck[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ok_1), frameWidth, frameHeight, false);
        okCheck[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ok_2), frameWidth, frameHeight, false);
        okCheck[2] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ok_3), frameWidth, frameHeight, false);
        okCheck[3] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ok_4), frameWidth, frameHeight, false);
        okCheck[4] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ok_5), frameWidth, frameHeight, false);
        okCheck[5] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ok_6), frameWidth, frameHeight, false);


        posX = x;
        posY = y;

    }

    public Bitmap getOkCheck(int frame) {
        return okCheck[frame];
    }

    public int getOkPosX(){
        return posX;
    }

    public int getOkPosY(){
        return posY;
    }

}
