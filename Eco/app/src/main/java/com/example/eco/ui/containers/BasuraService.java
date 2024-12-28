package com.example.eco.ui.containers;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BasuraService {
    private RequestQueue requestQueue;

    // Constructor que inicializa la cola de peticiones
    public BasuraService(Context context) {
        this.requestQueue = Volley.newRequestQueue(context);
    }

    // Método para obtener todas las basuras (GET)
    public void obtenerBasuras(String url, final BasuraCallback callback) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            List<Basura> basuras = new ArrayList<>();
                            // Procesar cada objeto en el arreglo JSON
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);

                                // Crear objeto Basura a partir del JSON
                                Basura basura = new Basura(
                                        jsonObject.getString("name"),         // Obtener el nombre
                                        jsonObject.getString("solidWaste")    // Obtener el tipo de residuos
                                );

                                basuras.add(basura); // Agregar a la lista
                            }
                            callback.onSuccess(basuras); // Devolver la lista de basuras en el callback
                        } catch (JSONException e) {
                            callback.onError("Error al procesar los datos del JSON: " + e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onError("Error en la solicitud: " + error.getMessage());
            }
        });

        // Añadir la solicitud a la cola de peticiones
        requestQueue.add(jsonArrayRequest);
    }
}
