package com.iessanalberto.dam1.jdbc.screens;

import com.iessanalberto.dam1.jdbc.models.Sala;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class SalaScreen {
    private HBox root = new HBox();
    GridPane gridPane = new GridPane();
    private Button[][] butacas;

    public SalaScreen(Sala sala) {
        butacas = new Button[sala.getNumFilas()][sala.getNumColumnas()];
        for (int fila = 0; fila <sala.getNumFilas();fila++) {
            for (int columna = 0; columna <sala.getNumColumnas();columna++){
                butacas[fila][columna] = new Button();
               gridPane.add(butacas[fila][columna],columna,fila);
            }
        }
        root.getChildren().addAll(gridPane);
    }

    public HBox getRoot() {
        return root;
    }
}
