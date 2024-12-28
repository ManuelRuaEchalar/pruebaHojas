package com.example.eco.ui.containers;

import java.util.ArrayList;

public class Places {
    private ArrayList<Marker> marcadores;

    public Places() {
        marcadores = new ArrayList<>();
        // Agregar marcadores iniciales
        /*marcadores.add(new Marker("Contenedor 1", "descripcion 1", -19.043547,-65.2502863));
        marcadores.add(new Marker("Contenedor 2", "descripcion 2", -19.0442463,-65.2557409));
        marcadores.add(new Marker("Contenedor 3", "descripcion 3", -19.0458885,-65.2538822));*/
    }

    public void agregarMarcador(Marker marcador) {
        marcadores.add(marcador);
    }

    public ArrayList<Marker> obtenerMarcadores() {
        return marcadores;
    }
}
