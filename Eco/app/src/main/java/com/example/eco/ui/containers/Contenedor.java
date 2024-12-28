package com.example.eco.ui.containers;

public class Contenedor {
    private int id;
    private String address;
    private String country;
    private String garbageCode;
    private double latitude;
    private double longitude;

    // Constructor
    public Contenedor(int id, String address, String country, String garbageCode, double latitude, double longitude) {
        this.id = id;
        this.address = address;
        this.country = country;
        this.garbageCode = garbageCode;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getGarbageCode() { return garbageCode; }
    public void setGarbageCode(String garbageCode) { this.garbageCode = garbageCode; }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }
}
