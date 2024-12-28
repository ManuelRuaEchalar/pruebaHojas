package com.example.eco.ui.containers;

import android.content.Context;
import android.util.Log;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class BasuraManager {
    private List<Basura> basuraList;
    private BasuraService basuraService;  // Servicio para obtener datos desde el servidor
    private static final String TAG = "BasuraManager";
    private static final String URL_API = "http://192.168.199.5:8000/api/garbage";

    // Constructor inicializa la lista vacía y el servicio
    public BasuraManager(Context context) {
        this.basuraList = new ArrayList<>();  // Lista vacía inicialmente
        this.basuraService = new BasuraService(context);  // Inicializa el servicio

        // Llamar al método para obtener los datos del servicio
        cargarBasurasDesdeServicio();
    }

    // Método para recuperar los datos del servicio web y llenar la lista
    private void cargarBasurasDesdeServicio() {
        //String url = "@string/basura_service_url";  // Cambia esta URL por la de tu servicio
        basuraService.obtenerBasuras(URL_API, new BasuraCallback() {
            @Override
            public void onSuccess(List<Basura> basuras) {
                // Llenar la lista de basuras con los datos recibidos del servicio
                basuraList.clear();  // Limpiar la lista antes de añadir nuevos datos
                basuraList.addAll(basuras);  // Añadir todos los elementos recibidos
                Log.d(TAG, "Lista de basuras cargada con éxito desde el servicio. Tamaño: " + basuraList.size());
            }

            @Override
            public void onError(String mensajeError) {
                // Manejar el error en caso de que la solicitud falle
                Log.e(TAG, "Error al cargar basuras desde el servicio: " + mensajeError);
            }
        });
    }

    // Método para buscar coincidencias en name o solidWaste dentro de la lista de basuras
    public List<Basura> buscarBasura(String query) {
        List<Basura> resultados = new ArrayList<>();
        String queryNormalizado = normalizar(query);  // Normalizar la consulta

        for (Basura basura : basuraList) {
            // Normalizar los campos 'name' y 'solidWaste' para compararlos sin tildes
            String nameNormalizado = normalizar(basura.getName());
            String solidWasteNormalizado = normalizar(basura.getSolidWaste());

            // Realizar la búsqueda ignorando las tildes
            if (nameNormalizado.contains(queryNormalizado) || solidWasteNormalizado.contains(queryNormalizado)) {
                resultados.add(basura);  // Añadir a los resultados si hay coincidencia
            }
        }
        return resultados;  // Devolver los resultados encontrados
    }

    // Método para normalizar una cadena eliminando las tildes
    private String normalizar(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalized).replaceAll("").toLowerCase();
    }
}
