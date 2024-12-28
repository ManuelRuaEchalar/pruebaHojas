package com.example.eco.ui.containers;

public interface ContenedorCallback {
    void onSuccess(Object response);  // Puede devolver un solo contenedor o una lista de contenedores
    void onError(String error);
}
