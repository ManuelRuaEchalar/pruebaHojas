package com.example.eco.ui.games.minigamedos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.eco.R;

import java.util.Random;

public class Spike {
    Bitmap spike1;  // Imágenes de los spikes
    int spikeFrame = 0;  // Animación del frame actual del spike
    float spikeX, spikeY, spikeVelocity;  // Coordenadas y velocidad del spike
    Context context;
    Random random;
    boolean isRewardSpike;  // Para determinar si es un spike que otorga recompensas o uno dañino

    public Spike(Context context, boolean isRewardSpike) {
        this.context = context;
        this.isRewardSpike = isRewardSpike;
        random = new Random();

        // Escalar todas las imágenes a 100x100
        if (isRewardSpike) {
            // Cargar las imágenes para el spike de recompensa, cambiando según el randomImageIndex
            switch (MiniGameDosActivity.randomImageIndex) {
                case 1:
                    spike1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.spike_reward_blue), 100, 100, false);
                    break;
                case 2:
                    spike1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.spike_reward_green), 100, 100, false);
                    break;
                case 3:
                    spike1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.spike_reward_yellow), 50, 100, false);
                    break;
                case 4:
                    spike1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.spike_reward_red), 100, 100, false);
                    break;
                case 5:
                    spike1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.spike_reward_gray), 100, 100, false);
                    break;
                case 6:
                    spike1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.spike_reward_black), 100, 100, false);
                    break;
            }
        } else {
            Random random = new Random();
            int randomspike;
            do{
                randomspike = random.nextInt(6) + 1;
            }while (randomspike == MiniGameDosActivity.randomImageIndex);

            switch (randomspike) {
                case 1:
                    spike1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.spike_reward_blue), 100, 100, false);
                    break;
                case 2:
                    spike1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.spike_reward_green), 100, 100, false);
                    break;
                case 3:
                    spike1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.spike_reward_yellow), 50, 100, false);
                    break;
                case 4:
                    spike1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.spike_reward_red), 100, 100, false);
                    break;
                case 5:
                    spike1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.spike_reward_gray), 100, 100, false);
                    break;
                case 6:
                    spike1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.spike_reward_black), 100, 100, false);
                    break;
            }
        }

        resetPosition();  // Inicializar la posición del spike
    }

    // Obtener la imagen del spike en el frame actual de la animación
    public Bitmap getSpike(int frame) {
            return spike1;
    }

    // Obtener la altura del spike
    public int getSpikeHeight() {
        return spike1.getHeight();
    }

    // Obtener el ancho del spike
    public int getSpikeWidth() {
        return spike1.getWidth();
    }

    // Restablecer la posición del spike en la parte superior de la pantalla
    public void resetPosition() {
        spikeX = random.nextInt(GameView.dWidth - getSpikeWidth());
        spikeY = -200 + random.nextInt(600) * -1;  // Posición inicial en la parte superior fuera de la pantalla
        spikeVelocity = 10 + random.nextInt(16);   // Velocidad aleatoria entre 10 y 25
    }
}
