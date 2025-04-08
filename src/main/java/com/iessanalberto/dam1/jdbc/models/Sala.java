package com.iessanalberto.dam1.jdbc.models;

public class Sala {
    private int idSala;
    private int numSala;
    private int numFilas;
    private int numColumnas;
    private int idProyeccion;

    public Sala(int idSala, int numSala, int numFilas, int numColumnas,int idProyeccion) {
        this.idSala = idSala;
        this.numSala = numSala;
        this.numFilas = numFilas;
        this.numColumnas = numColumnas;
        this.idProyeccion = idProyeccion;
    }

    public int getIdSala() {
        return idSala;
    }

    public int getNumSala() {
        return numSala;
    }

    public int getIdProyeccion() {
        return idProyeccion;
    }

    public int getNumFilas() {
        return numFilas;
    }

    public int getNumColumnas() {
        return numColumnas;
    }
}
