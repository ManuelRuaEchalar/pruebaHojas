package com.example.eco.ui.games;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.eco.R;

import java.util.Random;

public class GrayObject implements IObject {
    Bitmap grayObject;
    Random random;
    int grayObjectWidth, grayObjectHeight;
    int grayObjectX, grayObjectY;
    int color;



    public GrayObject(Context context, int x, int y) {
        color =2;
        random = new Random();
        grayObjectX = x;
        grayObjectY = y;

        //random from 0 to 4 int
        int randomNum = random.nextInt(5);
        grayObjectWidth = 150;
        grayObjectHeight = 150;
        switch (randomNum){
            case 0:
                grayObject = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bo_gray_obj1), grayObjectWidth, grayObjectHeight, false);
                break;
            case 1:
                grayObject = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bo_gray_obj2), grayObjectWidth, grayObjectHeight, false);
                break;
            case 2:
                grayObject = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bo_gray_obj3), grayObjectWidth, grayObjectHeight, false);
                break;
            case 3:
                grayObject = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bo_gray_obj4), grayObjectWidth, grayObjectHeight, false);
                break;
            case 4:
                grayObject = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bo_gray_obj5), grayObjectWidth, grayObjectHeight, false);
                break;
        }


    }

    @Override
    public Bitmap getObject() {
        return grayObject;
    }
    @Override
    public int getObjectWidth() {
        return grayObject.getWidth();
    }
    @Override
    public int getObjectHeight() {
        return grayObject.getHeight();
    }

    @Override
    public int getObjectX() {
        return grayObjectX;
    }
    @Override
    public int getObjectY() {
        return grayObjectY;
    }

    @Override
    public int getColor() {
        return color;
    }
    @Override
    public void setObjectX(int x) {
        grayObjectX = x;
    }
    @Override
    public void setObjectY(int y) {
        grayObjectY = y;
    }

}
