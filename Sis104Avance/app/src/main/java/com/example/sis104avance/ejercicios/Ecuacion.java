package com.example.sis104avance.ejercicios;

public class Ecuacion {

    private double a, b, c;

    public Ecuacion(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    // Método para calcular el discriminante
    private Double D() {
        return (this.b * this.b) - (4 * this.a * this.c);
    }

    // Método para calcular las raíces
    public String Raices() {
        String solucion;
        Double discriminante = D();

        if (discriminante == 0) {
            Double x = -this.b / (2 * this.a);  // Se corrige la fórmula
            solucion = "X1 = X2 = " + x;
        } else if (discriminante > 0) {
            Double x1 = (-this.b + Math.sqrt(discriminante)) / (2 * this.a);  // Se corrigió el paréntesis y la fórmula
            Double x2 = (-this.b - Math.sqrt(discriminante)) / (2 * this.a);
            solucion = "X1 = " + x1 + " , X2 = " + x2;
        } else {
            Double parteReal = -this.b / (2 * this.a);
            Double parteImag = Math.sqrt(Math.abs(discriminante)) / (2 * this.a);
            solucion = "X1 = " + parteReal + " + " + parteImag + "i, X2 = " + parteReal + " - " + parteImag + "i";
        }

        return solucion;
    }
}
