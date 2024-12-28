package com.example.eco.ui.games;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.eco.R;

import java.util.Random;

public class RedObject implements IObject {
    Bitmap redObject;
    Random random;
    int redObjectWidth, redObjectHeight;
    int redObjectX, redObjectY;
    int color;



    public RedObject(Context context, int x, int y) {
        color =1;
        random = new Random();
        redObjectX = x;
        redObjectY = y;

        //random from 0 to 4 int
        int randomNum = random.nextInt(5);
        redObjectWidth = 150;
        redObjectHeight = 150;
        switch (randomNum){
            case 0:
                redObject = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bo_red_obj1), redObjectWidth, redObjectHeight, false);
                break;
            case 1:
                redObject = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bo_red_obj2), redObjectWidth, redObjectHeight, false);
                break;
            case 2:
                redObject = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bo_red_obj3), redObjectWidth, redObjectHeight, false);
                break;
            case 3:
                redObject = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bo_red_obj4), redObjectWidth, redObjectHeight, false);
                break;
            case 4:
                redObject = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bo_red_obj5), redObjectWidth, redObjectHeight, false);
                break;
        }


    }

    @Override
    public Bitmap getObject() {
        return redObject;
    }
    @Override
    public int getObjectWidth() {
        return redObject.getWidth();
    }
    @Override
    public int getObjectHeight() {
        return redObject.getHeight();
    }

    @Override
    public int getObjectX() {
        return redObjectX;
    }
    @Override
    public int getObjectY() {
        return redObjectY;
    }

    @Override
    public int getColor() {
        return color;
    }
    public void setObjectX(int x) {
        redObjectX = x;
    }
    @Override
    public void setObjectY(int y) {
        redObjectY = y;
    }

}
