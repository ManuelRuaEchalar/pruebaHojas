package com.example.eco.ui.info.aprendiendo;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

import androidx.core.content.ContextCompat;

import com.example.eco.R;

public class ColorGradientUtil {
    public static Drawable getGradientBackground(Context context, int imageResourceId) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);

        if (imageResourceId == R.drawable.learn_background_bo_red) {
            gradientDrawable.setColors(new int[]{
                    ContextCompat.getColor(context, R.color.red_gradient_start),
                    ContextCompat.getColor(context, R.color.red_gradient_end)
            });
        } else if (imageResourceId == R.drawable.learn_background_bo_green) {
            gradientDrawable.setColors(new int[]{
                    ContextCompat.getColor(context, R.color.green_gradient_start),
                    ContextCompat.getColor(context, R.color.green_gradient_end)
            });
        } else if (imageResourceId == R.drawable.learn_background_bo_blue) {
            gradientDrawable.setColors(new int[]{
                    ContextCompat.getColor(context, R.color.blue_gradient_start),
                    ContextCompat.getColor(context, R.color.blue_gradient_end)
            });
        } else if (imageResourceId == R.drawable.learn_background_bo_yellow) {
            gradientDrawable.setColors(new int[]{
                    ContextCompat.getColor(context, R.color.yellow_gradient_start),
                    ContextCompat.getColor(context, R.color.yellow_gradient_end)
            });
        } else if (imageResourceId == R.drawable.learn_background_bo_black) {
            gradientDrawable.setColors(new int[]{
                    ContextCompat.getColor(context, R.color.black_gradient_start),
                    ContextCompat.getColor(context, R.color.black_gradient_end)
            });
        } else if (imageResourceId == R.drawable.learn_background_bo_gray ||
                imageResourceId == R.drawable.learn_background_bo_silver) {
            gradientDrawable.setColors(new int[]{
                    ContextCompat.getColor(context, R.color.gray_gradient_start),
                    ContextCompat.getColor(context, R.color.gray_gradient_end)
            });
        } else {
            gradientDrawable.setColor(ContextCompat.getColor(context, android.R.color.darker_gray));
        }

        gradientDrawable.setGradientType(GradientDrawable.LINEAR_GRADIENT);
        gradientDrawable.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);

        return gradientDrawable;
    }
}