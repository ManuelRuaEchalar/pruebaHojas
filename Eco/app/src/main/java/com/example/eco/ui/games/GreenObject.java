package com.example.eco.ui.games;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.eco.R;

import java.util.Random;

public class GreenObject implements IObject {
    Bitmap greenObject;
    Random random;
    int greenObjectWidth, greenObjectHeight;
    int greenObjectX, greenObjectY;
    int color;



    public GreenObject(Context context, int x, int y) {
        color =4;
        random = new Random();
        greenObjectX = x;
        greenObjectY = y;

        //random from 0 to 4 int
        int randomNum = random.nextInt(5);
        greenObjectWidth = 150;
        greenObjectHeight = 150;
        switch (randomNum){
            case 0:
                greenObject = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bo_green_obj1), greenObjectWidth, greenObjectHeight, false);
                break;
            case 1:
                greenObject = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bo_green_obj2), greenObjectWidth, greenObjectHeight, false);
                break;
            case 2:
                greenObject = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bo_green_obj3), greenObjectWidth, greenObjectHeight, false);
                break;
            case 3:
                greenObject = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bo_green_obj4), greenObjectWidth, greenObjectHeight, false);
                break;
            case 4:
                greenObject = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bo_green_obj5), greenObjectWidth, greenObjectHeight, false);
                break;
        }


    }

    @Override
    public Bitmap getObject() {
        return greenObject;
    }
    @Override
    public int getObjectWidth() {
        return greenObject.getWidth();
    }
    @Override
    public int getObjectHeight() {
        return greenObject.getHeight();
    }

    @Override
    public int getObjectX() {
        return greenObjectX;
    }
    @Override
    public int getObjectY() {
        return greenObjectY;
    }

    @Override
    public int getColor() {
        return color;
    }
    @Override
    public void setObjectX(int x) {
        greenObjectX = x;
    }
    @Override
    public void setObjectY(int y) {
        greenObjectY = y;
    }

}
