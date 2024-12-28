package com.example.eco.ui.games;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.eco.R;

import java.util.Random;

public class BlueObject implements IObject {
    Bitmap blueObject;
    Random random;
    int blueObjectWidth, blueObjectHeight;
    int blueObjectX, blueObjectY;
    int color;



    public BlueObject(Context context, int x, int y) {
        color =5;
        random = new Random();
        blueObjectX = x;
        blueObjectY = y;

        //random from 0 to 4 int
        int randomNum = random.nextInt(5);
        blueObjectWidth = 150;
        blueObjectHeight = 150;
        switch (randomNum){
            case 0:
                blueObject = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bo_blue_obj1), blueObjectWidth, blueObjectHeight, false);
                break;
            case 1:
                blueObject = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bo_blue_obj2), blueObjectWidth, blueObjectHeight, false);
                break;
            case 2:
                blueObject = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bo_blue_obj3), blueObjectWidth, blueObjectHeight, false);
                break;
            case 3:
                blueObject = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bo_blue_obj4), blueObjectWidth, blueObjectHeight, false);
                break;
            case 4:
                blueObject = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bo_blue_obj5), blueObjectWidth, blueObjectHeight, false);
                break;
        }


    }

    @Override
    public Bitmap getObject() {
        return blueObject;
    }
    @Override
    public int getObjectWidth() {
        return blueObject.getWidth();
    }
    @Override
    public int getObjectHeight() {
        return blueObject.getHeight();
    }

    @Override
    public int getObjectX() {
        return blueObjectX;
    }
    @Override
    public int getObjectY() {
        return blueObjectY;
    }

    @Override
    public int getColor() {
        return color;
    }
    @Override
    public void setObjectX(int x) {
        blueObjectX = x;
    }
    @Override
    public void setObjectY(int y) {
        blueObjectY = y;
    }

}
