package com.iessanalberto.dam1.jdbc.screens;

import com.iessanalberto.dam1.jdbc.models.Butaca;
import com.iessanalberto.dam1.jdbc.models.Sala;
import com.iessanalberto.dam1.jdbc.models.Usuario;
import com.iessanalberto.dam1.jdbc.services.SalaServices;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Map;

public class SalaScreen {
    private HBox root = new HBox();
    GridPane gridPane = new GridPane();
    private Button[][] butacas;
    private ArrayList<Pair<Integer,Integer>> butacasReservadas = new ArrayList<>();

    private Button btnReservar = new Button("Reservar");
    SalaServices salaServices = new SalaServices();

    public SalaScreen(Sala sala, Usuario usuario) {
        butacas = new Button[sala.getNumFilas()][sala.getNumColumnas()];
        try {
            Map<Integer,Butaca> listaButacas = salaServices.comprobarDisponibilidad(sala.getIdSala(),sala.getNumSala(),sala.getIdProyeccion());

        for (int fila = 0; fila <sala.getNumFilas();fila++) {
            for (int columna = 0; columna <sala.getNumColumnas();columna++){

                           butacas[fila][columna] = new Button();
                for (Butaca butaca: listaButacas.values()) {
                    if (butaca.getFila() == fila+1 && butaca.getColumna() == columna+1) {
                        if (butaca.isReservada()) {
                            butacas[fila][columna].setStyle("-fx-background-color: red");
                            butacas[fila][columna].setDisable(true);
                        }
                    }
                }
               gridPane.add(butacas[fila][columna],columna,fila);
                int finalFila = fila;
                int finalColumna = columna;
                butacas[fila][columna].setOnAction(event -> {
                    if (!butacas[finalFila][finalColumna].getStyle().contains("-fx-background-color: green")) {
                        butacas[finalFila][finalColumna].setStyle("-fx-background-color: green");
                        butacasReservadas.add(new Pair<>(finalFila+1,finalColumna+1));
                    } else {
                        butacas[finalFila][finalColumna].setStyle("");
                        butacasReservadas.remove(new Pair<>(finalFila+1,finalColumna+1));

                    }
                });
            }
        }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        root.getChildren().addAll(gridPane,btnReservar);

        btnReservar.setOnAction(event -> {
                for (Pair pair: butacasReservadas) {
                    System.out.println(pair.getKey()+","+pair.getValue());
                }
        });

    }

    public HBox getRoot() {
        return root;
    }
}
