package com.example.eco.ui.games.minigamecuatro;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import androidx.core.content.res.ResourcesCompat;

import com.example.eco.R;

public class GameView extends View {
    private List<Comidas> comidas;
    private int dWidth, dHeight;
    private int pointSize = 50;
    private String currentDirection = "right";
    private List<int[]> snakeBody;
    private Paint snakePaint, backgroundPaint, controlPaint;
    private Bitmap headSnakeBitmap;
    private Handler handler;
    Context context;
    private Random random;
    private Comidas comida1, comida2;
    private final long UPDATE_MILLIS = 150;
    private int puntos = 0;
    private boolean gameOver = false;
    Paint textPaint = new Paint();
    private Bitmap backgroundBitmap;
    private Paint snakeBorderPaint;

    public GameView(Context context) {
        super(context);
        init(context);
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        snakePaint = new Paint();
        snakePaint.setStyle(Paint.Style.FILL);
        backgroundPaint = new Paint();
        backgroundPaint.setColor(getResources().getColor(android.R.color.darker_gray));
        controlPaint = new Paint();
        controlPaint.setColor(getResources().getColor(android.R.color.holo_green_dark));
        handler = new Handler();
        random = new Random();
        // Cargar la fuente personalizada desde res/font
        Typeface kenneyFont = ResourcesCompat.getFont(context, R.font.kenney_blocks);
        textPaint.setTypeface(kenneyFont); // Aplicar la fuente personalizada

        snakeBorderPaint = new Paint();
        snakeBorderPaint.setStyle(Paint.Style.STROKE);
        snakeBorderPaint.setColor(Color.WHITE); // Color del borde
        snakeBorderPaint.setStrokeWidth(6); // Grosor del borde

        // Cargar el fondo
        backgroundBitmap = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(context.getResources(), R.drawable.fondo),
                getResources().getDisplayMetrics().widthPixels,
                getResources().getDisplayMetrics().heightPixels * 3 / 4,
                false
        );

        // Selección de color e imagen de la serpiente según el valor random en MainActivity
        int randomImageIndex = MiniGameCuatroActivity.randomImageIndex;
        setupSnakeAppearance(context, randomImageIndex);

        // Inicializar el cuerpo de la serpiente con 4 segmentos
        snakeBody = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            snakeBody.add(new int[]{0, 0});
        }

        // Inicializar las comidas
        comidas = new ArrayList<>();
        comidas.add(new Comidas(context, true));  // Comida para aumentar puntos
        for (int i = 0; i < 4; i++) {
            comidas.add(new Comidas(context, false));  // Comida para disminuir puntos
        }

        handler.postDelayed(runnable, UPDATE_MILLIS);
    }


    private void setupSnakeAppearance(Context context, int randomImageIndex) {
        switch (randomImageIndex) {
            case 1:
                snakePaint.setColor(Color.BLUE);
                headSnakeBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.head_snake1), pointSize, pointSize, false);
                break;
            case 2:
                snakePaint.setColor(Color.GREEN);
                headSnakeBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.head_snake2), pointSize, pointSize, false);
                break;
            case 3:
                snakePaint.setColor(Color.YELLOW);
                headSnakeBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.head_snake3), pointSize, pointSize, false);
                break;
            case 4:
                snakePaint.setColor(Color.RED);
                headSnakeBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.head_snake4), pointSize, pointSize, false);
                break;
            case 5:
                snakePaint.setColor(Color.GRAY);
                headSnakeBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.head_snake5), pointSize, pointSize, false);
                break;
            case 6:
                snakePaint.setColor(Color.BLACK);
                headSnakeBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.head_snake6), pointSize, pointSize, false);
                break;
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        dWidth = w;
        dHeight = h;

        // Posicionar la serpiente en el centro con tamaño fijo de 3 cuadros
        int startX = dWidth / 2;
        int startY = dHeight / 2;
        snakeBody.clear();
        for (int i = 0; i < 3; i++) {
            snakeBody.add(new int[]{startX - i * pointSize, startY});
        }

        // Generar la posición de las comidas sin superposición y lejos de la serpiente
        for (Comidas comida : comidas) {
            comida.spawnFood(dWidth, dHeight, comidas, snakeBody);
        }
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Dibujar el fondo
        canvas.drawBitmap(backgroundBitmap, 0, 0, null);

        // Dibujar la serpiente
        for (int i = 0; i < snakeBody.size(); i++) {
            int[] segment = snakeBody.get(i);
            if (i == 0) {
                // Dibujar la cabeza de la serpiente
                canvas.drawBitmap(headSnakeBitmap, segment[0], segment[1], null);
            } else {
                // Dibujar el borde del segmento
                canvas.drawRect(segment[0], segment[1], segment[0] + pointSize, segment[1] + pointSize, snakeBorderPaint);
                // Dibujar los segmentos del cuerpo
                canvas.drawRect(segment[0], segment[1], segment[0] + pointSize, segment[1] + pointSize, snakePaint);
            }
        }

        // Dibujar las comidas
        for (Comidas comida : comidas) {
            canvas.drawBitmap(comida.getFoodBitmap(), comida.foodX, comida.foodY, null);
        }

        // Dibujar los puntos
        textPaint.setTextSize(80);
        textPaint.setColor(Color.rgb(255, 215, 0)); // Cambiar a color dorado para destacar
        canvas.drawText("PUNTOS: " + puntos, 20, 100, textPaint);

        // Dibujar los controles
        drawControls(canvas);
    }





    private void drawControls(Canvas canvas) {
        int controlHeight = dHeight / 4;
        int controlWidth = dWidth / 3;

        // Paint para los botones
        Paint buttonPaint = new Paint();
        buttonPaint.setColor(Color.WHITE); // Fondo de los botones
        buttonPaint.setStyle(Paint.Style.FILL);
        buttonPaint.setShadowLayer(10, 0, 0, Color.LTGRAY); // Sombra suave

        // Paint para el texto
        Paint textPaint = new Paint();
        textPaint.setColor(Color.BLACK); // Texto negro
        textPaint.setTextSize(70); // Tamaño del texto
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setFakeBoldText(true); // Hacer el texto más destacado

        // Botón de dirección arriba
        canvas.drawRoundRect(controlWidth, dHeight * 3 / 4, controlWidth * 2, dHeight * 3 / 4 + controlHeight / 2, 30, 30, buttonPaint);
        canvas.drawText("↑", controlWidth + controlWidth / 2, dHeight * 3 / 4 + controlHeight / 4 + 20, textPaint);

        // Botón de dirección abajo
        canvas.drawRoundRect(controlWidth, dHeight * 3 / 4 + controlHeight / 2, controlWidth * 2, dHeight, 30, 30, buttonPaint);
        canvas.drawText("↓", controlWidth + controlWidth / 2, dHeight * 3 / 4 + controlHeight * 3 / 4 + 20, textPaint);

        // Botón de dirección izquierda
        canvas.drawRoundRect(0, dHeight * 3 / 4 + controlHeight / 4, controlWidth, dHeight * 3 / 4 + controlHeight * 3 / 4, 30, 30, buttonPaint);
        canvas.drawText("←", controlWidth / 2, dHeight * 3 / 4 + controlHeight / 2 + 20, textPaint);

        // Botón de dirección derecha
        canvas.drawRoundRect(controlWidth * 2, dHeight * 3 / 4 + controlHeight / 4, dWidth, dHeight * 3 / 4 + controlHeight * 3 / 4, 30, 30, buttonPaint);
        canvas.drawText("→", controlWidth * 2 + controlWidth / 2, dHeight * 3 / 4 + controlHeight / 2 + 20, textPaint);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();

        // Detectar en qué zona está el toque para cambiar la dirección
        if (touchY >= dHeight * 3 / 4) {
            int controlHeight = dHeight / 4;
            int controlWidth = dWidth / 3;

            if (touchY < dHeight * 3 / 4 + controlHeight / 2 && touchX > controlWidth && touchX < controlWidth * 2) {
                if (!currentDirection.equals("down")) currentDirection = "up";
            } else if (touchY >= dHeight * 3 / 4 + controlHeight / 2 && touchX > controlWidth && touchX < controlWidth * 2) {
                if (!currentDirection.equals("up")) currentDirection = "down";
            } else if (touchY >= dHeight * 3 / 4 + controlHeight / 4 && touchY < dHeight * 3 / 4 + controlHeight * 3 / 4 && touchX < controlWidth) {
                if (!currentDirection.equals("right")) currentDirection = "left";
            } else if (touchY >= dHeight * 3 / 4 + controlHeight / 4 && touchY < dHeight * 3 / 4 + controlHeight * 3 / 4 && touchX > controlWidth * 2) {
                if (!currentDirection.equals("left")) currentDirection = "right";
            }
        }

        return true;
    }

    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (!gameOver) {  // Solo mover la serpiente si el juego no ha terminado
                moveSnake();
                checkFoodCollision();
                invalidate();
                handler.postDelayed(this, UPDATE_MILLIS);
            }
        }
    };


    private void moveSnake() {
        for (int i = snakeBody.size() - 1; i > 0; i--) {
            snakeBody.get(i)[0] = snakeBody.get(i - 1)[0];
            snakeBody.get(i)[1] = snakeBody.get(i - 1)[1];
        }

        int[] head = snakeBody.get(0);
        switch (currentDirection) {
            case "up":
                head[1] -= pointSize;
                break;
            case "down":
                head[1] += pointSize;
                break;
            case "left":
                head[0] -= pointSize;
                break;
            case "right":
                head[0] += pointSize;
                break;
        }

        // Verificar si la cabeza choca con el borde
        if (head[0] < 0 || head[0] > dWidth - pointSize || head[1] < 0 || head[1] > dHeight * 3 / 4 - pointSize) {
            endGame(false);
        }

    }

    private void endGame(boolean won) {
        gameOver = true;  // Detener el juegox
        if (context instanceof Activity) {  // Asegurarse de que el contexto es una instancia de Activity
            Intent intent = new Intent(context, GameOverView.class);
            intent.putExtra("status", won ? "won" : "lost");
            intent.putExtra("points", puntos);  // Pasar los puntos a GameOverView
            context.startActivity(intent);
            ((Activity) context).finish();  // Finalizar la actividad actual si es necesario
        }
    }



    private void checkFoodCollision() {
        int[] head = snakeBody.get(0);

        // Verificar colisión con cada comida
        for (Comidas comida : comidas) {
            if (head[0] + pointSize >= comida.foodX && head[0] <= comida.foodX + comida.getFoodBitmap().getWidth() &&
                    head[1] + pointSize >= comida.foodY && head[1] <= comida.foodY + comida.getFoodBitmap().getHeight()) {

                // Actualizar puntos según el tipo de comida
                if (comida.isGrow()) {
                    puntos++;
                } else {
                    puntos--;
                    if (puntos < 0) puntos = 0;
                }

                // Revisar condición de victoria
                if (puntos >= 10) {
                    endGame(true);
                    return;
                }

                // Reposicionar todas las comidas sin superposición y lejos de la serpiente
                for (Comidas c : comidas) {
                    c.spawnFood(dWidth, dHeight, comidas, snakeBody);
                }

                // Salir del ciclo una vez que se detecta la colisión
                break;
            }
        }
    }








}
