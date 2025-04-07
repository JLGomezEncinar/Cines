package com.iessanalberto.dam1.jdbc.screens;

import com.iessanalberto.dam1.jdbc.models.Usuario;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;

public class ReservaScreen {
    private VBox root = new VBox();
    private HBox fila1 = new HBox();
    private HBox fila2 = new HBox();
    private HBox fila3 = new HBox();
    private HBox fila4 = new HBox();

    private Label lblFecha = new Label("Elige fecha");
    private DatePicker dtpFecha = new DatePicker();
    private Label lblPelicula = new Label("Elige película");
    ComboBox cmbPelicula = new ComboBox();
    private Label lblSala = new Label("Elige sala");
    ComboBox cmbSala = new ComboBox();
    private Label lblHora = new Label("Elige hora");
    ComboBox cmbHora = new ComboBox();
    Button btnReserva = new Button("Reserva");

    public ReservaScreen(Usuario usuario) {
    configurarLayout();
    agregarComponentes();
    }

    public VBox getRoot() {
        return root;
    }
    public void configurarLayout() {
        root.setPadding(new Insets(10));
        root.setSpacing(20);
        root.setAlignment(Pos.CENTER);
        fila1.setAlignment(Pos.CENTER_RIGHT);
        fila1.setPadding(new Insets(0, 20, 0, 0));
        fila1.setSpacing(5);
        fila2.setAlignment(Pos.CENTER_RIGHT);
        fila2.setPadding(new Insets(0, 20, 0, 0));
        fila2.setSpacing(5);
        fila3.setAlignment(Pos.CENTER_RIGHT);
        fila3.setPadding(new Insets(0, 20, 0, 0));
        fila3.setSpacing(5);
        fila4.setAlignment(Pos.CENTER_RIGHT);
        fila4.setPadding(new Insets(0, 20, 0, 0));
        fila4.setSpacing(5);
    }
    public void agregarComponentes () {
        LocalDate minDate = LocalDate.now();
        LocalDate maxDate = minDate.plusDays(6);
        dtpFecha.setDayCellFactory(d ->
                new DateCell() {
                    @Override public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        setDisable(item.isAfter(maxDate) || item.isBefore(minDate));
                    }});
        fila1.getChildren().addAll(lblFecha, dtpFecha);
        fila2.getChildren().addAll(lblPelicula, cmbPelicula);
        fila3.getChildren().addAll(lblSala, cmbSala);
        fila4.getChildren().addAll(lblHora, cmbHora);
        root.getChildren().addAll(fila1, fila2, fila3,fila4,btnReserva);
    }
}
