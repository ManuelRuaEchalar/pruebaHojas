package com.example.eco.ui.containers;

import java.util.List;

public interface BasuraCallback {
    void onSuccess(List<Basura> basuras);  // Devolverá una lista de objetos Basura
    void onError(String error);            // Manejará errores
}

