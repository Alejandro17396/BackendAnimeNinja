package com.alejandro.animeninja.bussines.model;

public enum Intensity {
   // SUPERLOW(5),
    LOW(5),
    MEDIUM(10),
    HIGH(15);
    //SUPERHIGH(20);

    private final int valor;

    Intensity(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}

