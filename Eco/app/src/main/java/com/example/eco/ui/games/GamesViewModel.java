package com.example.eco.ui.games;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GamesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public GamesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is games fragment"); // Cambia el texto según lo que desees mostrar
    }

    public LiveData<String> getText() {
        return mText;
    }
}