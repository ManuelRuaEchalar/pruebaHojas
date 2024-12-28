package com.example.eco.ui.info;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.eco.R;
import com.example.eco.ui.info.aprendiendo.ImageDialogFragment;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class ImageSliderAdapter extends RecyclerView.Adapter<ImageSliderAdapter.ImageViewHolder> {

    private List<Integer> imageList;
    private Context context;
    private Map<Integer, Integer> cardIdMapping;
    private Map<Integer, Integer> colorMapping;
    private Map<Integer, Integer> categoryImageMapping;

    public ImageSliderAdapter(Context context, List<Integer> imageList) {
        this.context = context;
        this.imageList = imageList;
        setupMappings();
    }

    private void setupMappings() {
        // Mapeo de imágenes a IDs de tarjetas
        cardIdMapping = new HashMap<>();
        cardIdMapping.put(R.drawable.learn_background_bo_red, 1);
        cardIdMapping.put(R.drawable.learn_background_bo_green, 5);
        cardIdMapping.put(R.drawable.learn_background_bo_blue, 2);
        cardIdMapping.put(R.drawable.learn_background_bo_yellow, 7);
        cardIdMapping.put(R.drawable.learn_background_bo_black, 4);
        cardIdMapping.put(R.drawable.learn_background_bo_gray, 6);
        cardIdMapping.put(R.drawable.learn_background_bo_silver, 3);

        categoryImageMapping = new HashMap<>();
        categoryImageMapping.put(R.drawable.learn_background_bo_red, R.drawable.organicos);
        categoryImageMapping.put(R.drawable.learn_background_bo_green, R.drawable.reciclables);
        categoryImageMapping.put(R.drawable.learn_background_bo_blue, R.drawable.papel);
        categoryImageMapping.put(R.drawable.learn_background_bo_yellow, R.drawable.plastico);
        categoryImageMapping.put(R.drawable.learn_background_bo_black, R.drawable.electricos);
        categoryImageMapping.put(R.drawable.learn_background_bo_gray, R.drawable.metal);
        categoryImageMapping.put(R.drawable.learn_background_bo_silver, R.drawable.vidrio);

        // Mapeo de imágenes a colores de fondo
        colorMapping = new HashMap<>();
        colorMapping.put(R.drawable.learn_background_bo_red, context.getColor(android.R.color.holo_red_dark));
        colorMapping.put(R.drawable.learn_background_bo_green, context.getColor(android.R.color.holo_green_dark));
        colorMapping.put(R.drawable.learn_background_bo_blue, context.getColor(android.R.color.holo_blue_dark));
        colorMapping.put(R.drawable.learn_background_bo_yellow, context.getColor(android.R.color.holo_orange_light));
        colorMapping.put(R.drawable.learn_background_bo_black, context.getColor(android.R.color.black));
        colorMapping.put(R.drawable.learn_background_bo_gray, context.getColor(android.R.color.darker_gray));
        colorMapping.put(R.drawable.learn_background_bo_silver, context.getColor(android.R.color.darker_gray));
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_image_slider, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        int currentImage = imageList.get(position);

        Glide.with(context)
                .load(currentImage)
                .into(holder.imageView);

        holder.imageView.setOnClickListener(v -> {
            if (position == 0) return;

            Integer cardId = cardIdMapping.get(currentImage);
            Integer backgroundColor = colorMapping.get(currentImage);
            Integer categoryImage = categoryImageMapping.get(currentImage);

            if (cardId != null && backgroundColor != null && categoryImage != null) {
                ImageDialogFragment dialogFragment = ImageDialogFragment.newInstance(
                        cardId,
                        backgroundColor,
                        categoryImage
                );
                dialogFragment.show(
                        ((AppCompatActivity) context).getSupportFragmentManager(),
                        "ImageDialog"
                );
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}