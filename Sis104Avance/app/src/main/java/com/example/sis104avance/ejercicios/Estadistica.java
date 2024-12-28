package com.example.sis104avance.ejercicios;

public class Estadistica {

    private int a;
    private int b;
    private int c;

    public Estadistica(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    private String barra(int n, int maxPantalla) {
        // Calcular la longitud escalada
        int longitudEscalada = Math.min(n * maxPantalla / 50, maxPantalla);
        String cad = "";
        for (int i = 0; i < longitudEscalada; i++) {
            cad += "*";
        }
        return cad;
    }

    public String Grafico() {
        int maxPantalla = 50;  // Definir el ancho máximo de la pantalla
        String cad = "";
        cad += barra(this.a, maxPantalla) + "\n";
        cad += barra(this.b, maxPantalla) + "\n";
        cad += barra(this.c, maxPantalla) + "\n";

        return cad;
    }


    private String[] barraVertical(int n, int maxPantalla) {
        // Calcular la longitud escalada
        int longitudEscalada;
        if (n>15){
            longitudEscalada = n * maxPantalla / 100000;

        } else {
            longitudEscalada = n;
        }
        String[] columna = new String[longitudEscalada];
        for (int i = 0; i < longitudEscalada; i++) {
            columna[i] = "*";
        }
        return columna;
    }

    public String GraficoVertical() {
        int maxPantalla = 15;  // Definir el ancho máximo de la pantalla

        // Crear las barras verticales como arrays de Strings
        String[] barraA = barraVertical(this.a, maxPantalla);
        String[] barraB = barraVertical(this.b, maxPantalla);
        String[] barraC = barraVertical(this.c, maxPantalla);

        // Determinar la altura máxima entre las barras
        int alturaMax = Math.max(barraA.length, Math.max(barraB.length, barraC.length));

        // Crear la matriz para las barras verticales
        String[][] matriz = new String[alturaMax][3];

        // Inicializar la matriz con espacios en blanco
        for (int i = 0; i < alturaMax; i++) {
            matriz[i][0] = " ";
            matriz[i][1] = " ";
            matriz[i][2] = " ";
        }

        // Rellenar la matriz desde abajo hacia arriba
        for (int i = 0; i < barraA.length; i++) {
            matriz[alturaMax - 1 - i][0] = "*";
        }
        for (int i = 0; i < barraB.length; i++) {
            matriz[alturaMax - 1 - i][1] = "*";
        }
        for (int i = 0; i < barraC.length; i++) {
            matriz[alturaMax - 1 - i][2] = "*";
        }

        // Construir la salida final a partir de la matriz
        String cad = "";
        for (int i = 0; i < alturaMax; i++) {
            cad += matriz[i][0] + "  " + matriz[i][1] + "  " + matriz[i][2] + "\n";
        }

        return cad;
    }
}
