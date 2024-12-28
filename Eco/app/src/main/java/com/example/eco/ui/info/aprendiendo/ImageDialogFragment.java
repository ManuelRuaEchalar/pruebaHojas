package com.example.eco.ui.info.aprendiendo;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import com.bumptech.glide.Glide;
import com.example.eco.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageDialogFragment extends DialogFragment {
    private static final String ARG_CARD_ID = "card_id";
    private static final String ARG_BACKGROUND_COLOR = "background_color";
    private static final String ARG_CATEGORY_IMAGE = "category_image";

    public static ImageDialogFragment newInstance(int cardId, int backgroundColor, int categoryImage) {
        ImageDialogFragment fragment = new ImageDialogFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_CARD_ID, cardId);
        args.putInt(ARG_BACKGROUND_COLOR, backgroundColor);
        args.putInt(ARG_CATEGORY_IMAGE, categoryImage);
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_dialog, container, false);

        ImageView categoryImageView = view.findViewById(R.id.categoryImageView);

        int cardId = getArguments().getInt(ARG_CARD_ID);
        int backgroundColor = getArguments().getInt(ARG_BACKGROUND_COLOR);
        int categoryImage = getArguments().getInt(ARG_CATEGORY_IMAGE);
        View dialogContainer = view.findViewById(R.id.dialogContainer);
        TextView datoTextView = view.findViewById(R.id.datoTextView);
        TextView datoExtraTextView = view.findViewById(R.id.datoExtraTextView);
        TextView listaResiduosTextView = view.findViewById(R.id.listaResiduosTextView);
        ProgressBar progressBar = view.findViewById(R.id.progressBar);

        Glide.with(requireContext())
                .load(categoryImage)
                .into(categoryImageView);

        // Set background color
        dialogContainer.setBackgroundColor(backgroundColor);

        // Cargar datos de la API
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<CardResponse> call = apiService.getCard(cardId);

        progressBar.setVisibility(View.VISIBLE);

        call.enqueue(new Callback<CardResponse>() {
            @Override
            public void onResponse(Call<CardResponse> call, Response<CardResponse> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body() != null) {
                    CardResponse card = response.body();
                    datoTextView.setText(card.getDato());

                    if (card.getDatoExtra() != null && !card.getDatoExtra().isEmpty()) {
                        datoExtraTextView.setText(card.getDatoExtra());
                        datoExtraTextView.setVisibility(View.VISIBLE);
                    } else {
                        datoExtraTextView.setVisibility(View.GONE);
                    }

                    if (card.getListaResiduos() != null && !card.getListaResiduos().isEmpty()) {
                        listaResiduosTextView.setText(card.getListaResiduos());
                        listaResiduosTextView.setVisibility(View.VISIBLE);
                    } else {
                        listaResiduosTextView.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<CardResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                // Manejar el error aquí
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            Window window = dialog.getWindow();
            if (window != null) {
                window.setLayout(
                        (int) (350 * getResources().getDisplayMetrics().density),
                        (int) (500 * getResources().getDisplayMetrics().density)
                );
            }
        }
    }
}