package com.example.eco.ui.games;

import android.graphics.Bitmap;

public interface IObject {
    public Bitmap getObject();
    public int getObjectWidth();
    public int getObjectHeight();
    public int getObjectX();
    public int getObjectY();
    public int getColor();
    public void setObjectX(int x);
    public void setObjectY(int y);

}
