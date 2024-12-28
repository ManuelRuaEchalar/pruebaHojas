package com.example.eco.ui.containers;

public class Basura {
    private String name;
    private String solidWaste;

    public Basura(String name, String solidWaste) {
        this.name = name;
        this.solidWaste = solidWaste;
    }

    public String getName() {
        return name;
    }

    public String getSolidWaste() {
        return solidWaste;
    }
}
