package com.example.eco.ui.info.aprendiendo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("api/card/{id}")
    Call<CardResponse> getCard(@Path("id") int id);
}
