package com.example.eco.ui.containers;

import android.content.Context;
import android.util.Log;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ContenedorManager {
    private static final String TAG = "ContenedorManager";
    private ContenedorService contenedorService;
    private static final String URL_API = "http://192.168.199.5:8000/api/container";

    public ContenedorManager(Context context) {
        this.contenedorService = new ContenedorService(context);
    }

    // Obtener todos los contenedores (GET) - Ahora retorna una lista de Contenedor
    public CompletableFuture<List<Contenedor>> obtenerTodosLosContenedores() {
        CompletableFuture<List<Contenedor>> future = new CompletableFuture<>();
        //String url = "@string/url_api_container";

        contenedorService.obtenerContenedores(URL_API, new ContenedorCallback() {
            @Override
            public void onSuccess(Object response) {
                List<Contenedor> contenedores = (List<Contenedor>) response;
                future.complete(contenedores); // Devolver lista de contenedores
            }

            @Override
            public void onError(String error) {
                Log.e(TAG, "Error al obtener los contenedores: " + error);
                future.completeExceptionally(new Exception(error));
            }
        });

        return future; // Retorna un futuro que se completará cuando llegue la respuesta
    }

    // Crear un nuevo contenedor (POST) - Ahora retorna el Contenedor creado
    public CompletableFuture<Contenedor> crearNuevoContenedor(String address, String country, String garbageCode, double latitude, double longitude) {
        CompletableFuture<Contenedor> future = new CompletableFuture<>();
        //String url = "@string/url_api_container";
        Contenedor nuevoContenedor = new Contenedor(0, address, country, garbageCode, latitude, longitude);

        contenedorService.crearContenedor(URL_API, nuevoContenedor, new ContenedorCallback() {
            @Override
            public void onSuccess(Object response) {
                Contenedor creado = (Contenedor) response;
                future.complete(creado); // Devolver el contenedor creado
            }

            @Override
            public void onError(String error) {
                Log.e(TAG, "Error al crear el contenedor: " + error);
                future.completeExceptionally(new Exception(error));
            }
        });

        return future; // Retorna un futuro que se completará cuando se cree el contenedor
    }

    // Actualizar un contenedor existente (PUT) - Ahora retorna el Contenedor actualizado
    public CompletableFuture<Contenedor> actualizarContenedor(int id, String address, String country, String garbageCode, double latitude, double longitude) {
        CompletableFuture<Contenedor> future = new CompletableFuture<>();
        String url = URL_API + "/" + id;
        Contenedor contenedorActualizado = new Contenedor(id, address, country, garbageCode, latitude, longitude);

        contenedorService.actualizarContenedor(url, contenedorActualizado, new ContenedorCallback() {
            @Override
            public void onSuccess(Object response) {
                Contenedor actualizado = (Contenedor) response;
                future.complete(actualizado); // Devolver el contenedor actualizado
            }

            @Override
            public void onError(String error) {
                Log.e(TAG, "Error al actualizar el contenedor: " + error);
                future.completeExceptionally(new Exception(error));
            }
        });

        return future; // Retorna un futuro que se completará cuando se actualice el contenedor
    }

    // Eliminar un contenedor (DELETE) - Ahora retorna un mensaje de éxito o error
    public CompletableFuture<String> eliminarContenedor(int id) {
        CompletableFuture<String> future = new CompletableFuture<>();
        String url = URL_API + "/" + id;

        contenedorService.eliminarContenedor(url, new ContenedorCallback() {
            @Override
            public void onSuccess(Object response) {
                future.complete("Contenedor eliminado con éxito"); // Mensaje de éxito
            }

            @Override
            public void onError(String error) {
                Log.e(TAG, "Error al eliminar el contenedor: " + error);
                future.completeExceptionally(new Exception(error));
            }
        });

        return future; // Retorna un futuro que se completará cuando se elimine el contenedor
    }
}
