package com.example.eco.ui.games.minigamecuatro;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.eco.R;

import java.util.Random;
import java.util.List;


public class Comidas {
    private Bitmap foodBitmap;
    public int foodX, foodY;
    private boolean isGrow;  // Define si es comida que aumenta el tamaño (true) o lo reduce (false)
    private Random random;
    private int pointSize = 80;

    public Comidas(Context context, boolean isGrow) {
        this.isGrow = isGrow;
        random = new Random();

        // Asignar la imagen de la comida según el tipo (grow o shrink)
        if (isGrow) {
            switch (MiniGameCuatroActivity.randomImageIndex) {
                case 1:
                    foodBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.food_growblue), pointSize, pointSize, false);
                    break;
                case 2:
                    foodBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.food_growgreen), pointSize, pointSize, false);
                    break;
                case 3:
                    foodBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.food_growyellow), pointSize, pointSize, false);
                    break;
                case 4:
                    foodBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.food_growred), pointSize, pointSize, false);
                    break;
                case 5:
                    foodBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.food_growgray), pointSize, pointSize, false);
                    break;
                case 6:
                    foodBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.food_growblack), pointSize, pointSize, false);
                    break;
            }

        } else {

            Random random = new Random();
            int randomfood;
            do{
                randomfood = random.nextInt(6) + 1;
            }while (randomfood == MiniGameCuatroActivity.randomImageIndex);

            switch (randomfood) {
                case 1:
                    foodBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.food_growblue), pointSize, pointSize, false);
                    break;
                case 2:
                    foodBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.food_growgreen), pointSize, pointSize, false);
                    break;
                case 3:
                    foodBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.food_growyellow), pointSize, pointSize, false);
                    break;
                case 4:
                    foodBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.food_growred), pointSize, pointSize, false);
                    break;
                case 5:
                    foodBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.food_growgray), pointSize, pointSize, false);
                    break;
                case 6:
                    foodBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.food_growblack), pointSize, pointSize, false);
                    break;
            }
        }
    }

    public void spawnFood(int dWidth, int dHeight, List<Comidas> allFoods, List<int[]> snakeBody) {
        boolean isPositionValid;

        do {
            // Generar coordenadas aleatorias dentro del área jugable
            foodX = random.nextInt(dWidth - pointSize);
            foodY = random.nextInt(dHeight * 3 / 4 - pointSize);

            // Validar que no se solapen con las posiciones de otras comidas
            isPositionValid = true;
            for (Comidas food : allFoods) {
                if (food != this &&
                        Math.abs(food.foodX - foodX) < pointSize &&
                        Math.abs(food.foodY - foodY) < pointSize) {
                    isPositionValid = false;
                    break;
                }
            }

            // Validar que no esté demasiado cerca de la serpiente
            for (int[] segment : snakeBody) {
                if (Math.abs(segment[0] - foodX) < pointSize * 2 &&
                        Math.abs(segment[1] - foodY) < pointSize * 2) {
                    isPositionValid = false;
                    break;
                }
            }
        } while (!isPositionValid);
    }



    public Bitmap getFoodBitmap() {
        return foodBitmap;
    }

    public boolean isGrow() {
        return isGrow;
    }
}
