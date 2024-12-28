package com.example.eco.ui.games;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.eco.R;

import java.util.Random;

public class Spike {
    Bitmap spike;
    int spikeX,spikeY, spikeVelocity;
    Random random;
    public Spike(Context context){
        spike=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bala),50,50,false);

        random=new Random();
        resetPosition();

    }
    public Bitmap getSpike(){
        return spike;
    }
    public int getSpikeWidth(){
        return spike.getWidth();
    }
    public int getSpikeHeight() {
        return spike.getHeight();
    }

    public void resetPosition(){
        spikeX= GameView.dWidth/2;
        spikeY= -200;
        spikeVelocity=200;

    }

}
