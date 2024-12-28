package com.example.eco.ui.containers;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContenedorService {
    private RequestQueue requestQueue;

    // Constructor que inicializa la cola de peticiones
    public ContenedorService(Context context) {
        this.requestQueue = Volley.newRequestQueue(context);
    }

    // Método para obtener todos los contenedores (GET)
    public void obtenerContenedores(String url, final ContenedorCallback callback) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            List<Contenedor> contenedores = new ArrayList<>();
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                Contenedor contenedor = new Contenedor(
                                        jsonObject.getInt("id"),
                                        jsonObject.getString("address"),
                                        jsonObject.getString("country"),
                                        jsonObject.getString("garbageCode"),
                                        jsonObject.getDouble("latitude"),
                                        jsonObject.getDouble("longitude")
                                );
                                contenedores.add(contenedor);
                            }
                            callback.onSuccess(contenedores);
                        } catch (JSONException e) {
                            callback.onError("Error al procesar los datos del JSON");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onError("Error en la solicitud: " + error.getMessage());
            }
        });

        requestQueue.add(jsonArrayRequest);
    }

    // Método para obtener un contenedor específico por ID (GET)
    public void obtenerContenedorPorId(String url, final ContenedorCallback callback) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Contenedor contenedor = new Contenedor(
                                    response.getInt("id"),
                                    response.getString("address"),
                                    response.getString("country"),
                                    response.getString("garbageCode"),
                                    response.getDouble("latitude"),
                                    response.getDouble("longitude")
                            );
                            callback.onSuccess(contenedor);
                        } catch (JSONException e) {
                            callback.onError("Error al procesar los datos del JSON");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onError("Error en la solicitud: " + error.getMessage());
            }
        });

        requestQueue.add(jsonObjectRequest);
    }

    // Método para crear un contenedor (POST)
    public void crearContenedor(String url, final Contenedor contenedor, final ContenedorCallback callback) {
        Map<String, String> params = new HashMap<>();
        params.put("address", contenedor.getAddress());
        params.put("country", contenedor.getCountry());
        params.put("garbageCode", contenedor.getGarbageCode());
        params.put("latitude", String.valueOf(contenedor.getLatitude()));
        params.put("longitude", String.valueOf(contenedor.getLongitude()));

        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Contenedor nuevoContenedor = new Contenedor(
                                    response.getInt("id"),
                                    response.getString("address"),
                                    response.getString("country"),
                                    response.getString("garbageCode"),
                                    response.getDouble("latitude"),
                                    response.getDouble("longitude")
                            );
                            callback.onSuccess(nuevoContenedor);
                        } catch (JSONException e) {
                            callback.onError("Error al procesar el JSON de respuesta");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onError("Error en la solicitud: " + error.getMessage());
            }
        });

        requestQueue.add(postRequest);
    }

    // Método para actualizar un contenedor (PUT)
    public void actualizarContenedor(String url, final Contenedor contenedor, final ContenedorCallback callback) {
        Map<String, String> params = new HashMap<>();
        params.put("address", contenedor.getAddress());
        params.put("country", contenedor.getCountry());
        params.put("garbageCode", contenedor.getGarbageCode());
        params.put("latitude", String.valueOf(contenedor.getLatitude()));
        params.put("longitude", String.valueOf(contenedor.getLongitude()));

        JsonObjectRequest putRequest = new JsonObjectRequest(Request.Method.PUT, url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Contenedor contenedorActualizado = new Contenedor(
                                    response.getInt("id"),
                                    response.getString("address"),
                                    response.getString("country"),
                                    response.getString("garbageCode"),
                                    response.getDouble("latitude"),
                                    response.getDouble("longitude")
                            );
                            callback.onSuccess(contenedorActualizado);
                        } catch (JSONException e) {
                            callback.onError("Error al procesar el JSON de respuesta");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onError("Error en la solicitud: " + error.getMessage());
            }
        });

        requestQueue.add(putRequest);
    }

    // Método para eliminar un contenedor (DELETE)
    public void eliminarContenedor(String url, final ContenedorCallback callback) {
        StringRequest deleteRequest = new StringRequest(Request.Method.DELETE, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        callback.onSuccess("Contenedor eliminado correctamente");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onError("Error en la solicitud: " + error.getMessage());
            }
        });

        requestQueue.add(deleteRequest);
    }
}
