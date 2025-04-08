package com.iessanalberto.dam1.jdbc.models;

public class Butaca {
    private int fila;
    private int columna;
    private boolean reservada;

    public Butaca(int fila, int columna, boolean reservada) {
        this.fila = fila;
        this.columna = columna;
        this.reservada = reservada;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public boolean isReservada() {
        return reservada;
    }
}
