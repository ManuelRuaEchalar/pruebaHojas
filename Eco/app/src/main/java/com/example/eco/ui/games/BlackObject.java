package com.example.eco.ui.games;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.eco.R;

import java.util.Random;

public class BlackObject implements IObject {
    Bitmap blackObject;
    Random random;
    int blackObjectWidth, blackObjectHeight;
    int blackObjectX, blackObjectY;
    int color;



    public BlackObject(Context context, int x, int y) {
        color =3;
        random = new Random();
        blackObjectX = x;
        blackObjectY = y;

        //random from 0 to 4 int
        int randomNum = random.nextInt(5);
        blackObjectWidth = 150;
        blackObjectHeight = 150;
        switch (randomNum){
            case 0:
                blackObject = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bo_black_obj1), blackObjectWidth, blackObjectHeight, false);
                break;
            case 1:
                blackObject = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bo_black_obj2), blackObjectWidth, blackObjectHeight, false);
                break;
            case 2:
                blackObject = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bo_black_obj3), blackObjectWidth, blackObjectHeight, false);
                break;
            case 3:
                blackObject = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bo_black_obj4), blackObjectWidth, blackObjectHeight, false);
                break;
            case 4:
                blackObject = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bo_black_obj5), blackObjectWidth, blackObjectHeight, false);
                break;
        }


    }

    @Override
    public Bitmap getObject() {
        return blackObject;
    }
    @Override
    public int getObjectWidth() {
        return blackObject.getWidth();
    }
    @Override
    public int getObjectHeight() {
        return blackObject.getHeight();
    }

    @Override
    public int getObjectX() {
        return blackObjectX;
    }
    @Override
    public int getObjectY() {
        return blackObjectY;
    }

    @Override
    public int getColor() {
        return color;
    }
    @Override
    public void setObjectX(int x) {
        blackObjectX = x;
    }
    @Override
    public void setObjectY(int y) {
        blackObjectY = y;
    }


}
