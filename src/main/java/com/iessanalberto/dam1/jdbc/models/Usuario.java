package com.iessanalberto.dam1.jdbc.models;

public class Usuario {
    private String nombre;
    private String user;
    private String password;

    public Usuario(String nombre, String user, String password) {
        this.nombre = nombre;
        this.user = user;
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
