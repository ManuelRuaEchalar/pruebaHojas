package com.example.eco.ui.classify;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ClassifyViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ClassifyViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is classify fragment"); // Texto para el fragmento Classify
    }

    public LiveData<String> getText() {
        return mText;
    }
}
