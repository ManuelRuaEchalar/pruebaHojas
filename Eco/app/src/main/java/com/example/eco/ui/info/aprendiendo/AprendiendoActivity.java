package com.example.eco.ui.info.aprendiendo;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.example.eco.R;
import com.example.eco.ui.info.ImageSliderAdapter;

import java.util.Arrays;
import java.util.List;

public class AprendiendoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aprendiendo); // Asegúrate de tener el layout correcto

        // Configuración del ViewPager2
        ViewPager2 viewPager2 = findViewById(R.id.viewPager); // Debes agregar un ViewPager2 en tu layout

        // Lista de imágenes (reemplaza estos con tus recursos de imagen)
        List<Integer> imageList = Arrays.asList(
                R.drawable.back_learn_main,
                R.drawable.learn_background_bo_red,
                R.drawable.learn_background_bo_green,
                R.drawable.learn_background_bo_blue,
                R.drawable.learn_background_bo_yellow,
                R.drawable.learn_background_bo_black,
                R.drawable.learn_background_bo_gray,
                R.drawable.learn_background_bo_silver
        );

        // Configurar el adaptador para ViewPager2
        ImageSliderAdapter adapter = new ImageSliderAdapter(this, imageList);
        viewPager2.setAdapter(adapter);
    }
}
