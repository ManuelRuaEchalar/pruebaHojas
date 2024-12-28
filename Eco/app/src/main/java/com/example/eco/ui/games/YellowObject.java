package com.example.eco.ui.games;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.eco.R;

import java.util.Random;

public class YellowObject implements IObject {
    Bitmap yellowObject;
    Random random;
    int yellowObjectWidth, yellowObjectHeight;
    int yellowObjectX, yellowObjectY;
    int color;



    public YellowObject(Context context, int x, int y) {
        color =0;
        random = new Random();
        yellowObjectX = x;
        yellowObjectY = y;

        //random from 0 to 4 int
        int randomNum = random.nextInt(5);
        yellowObjectWidth = 150;
        yellowObjectHeight = 150;
        switch (randomNum){
            case 0:
                yellowObject = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bo_yellow_obj1), yellowObjectWidth, yellowObjectHeight, false);
                break;
            case 1:
                yellowObject = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bo_yellow_obj2), yellowObjectWidth, yellowObjectHeight, false);
                break;
            case 2:
                yellowObject = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bo_yellow_obj3), yellowObjectWidth, yellowObjectHeight, false);
                break;
            case 3:
                yellowObject = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bo_yellow_obj4), yellowObjectWidth, yellowObjectHeight, false);
                break;
            case 4:
                yellowObject = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bo_yellow_obj5), yellowObjectWidth, yellowObjectHeight, false);
                break;
        }


    }

    @Override
    public Bitmap getObject() {
        return yellowObject;
    }
    @Override
    public int getObjectWidth() {
        return yellowObject.getWidth();
    }
    @Override
    public int getObjectHeight() {
        return yellowObject.getHeight();
    }

    @Override
    public int getObjectX() {
        return yellowObjectX;
    }
    @Override
    public int getObjectY() {
        return yellowObjectY;
    }

    @Override
    public int getColor() {
        return color;
    }
    public void setObjectX(int x) {
        yellowObjectX = x;
    }
    @Override
    public void setObjectY(int y) {
        yellowObjectY = y;
    }

}
