package com.example.eco.ui.info.aprendiendo;

import com.google.gson.annotations.SerializedName;

public class CardResponse {
    @SerializedName("dato")
    private String dato;

    @SerializedName("dato_extra")
    private String datoExtra;

    @SerializedName("lista_residuos")
    private String listaResiduos;

    public String getDato() { return dato; }
    public String getDatoExtra() { return datoExtra; }
    public String getListaResiduos() { return listaResiduos; }
}
