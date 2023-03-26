package com.alejandro.animeninja.bussines.model.utils;

public class Elemento {
    private String tipo;
    private String nombre;

    public Elemento(String tipo, String nombre) {
        this.tipo = tipo;
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "(" + tipo + "," + nombre + ")";
    }
}