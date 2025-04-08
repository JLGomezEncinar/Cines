package com.iessanalberto.dam1.jdbc.screens;

import com.iessanalberto.dam1.jdbc.models.Sala;
import com.iessanalberto.dam1.jdbc.models.Usuario;
import com.iessanalberto.dam1.jdbc.navigation.Navigation;
import com.iessanalberto.dam1.jdbc.services.TicketServices;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservaScreen {
    private VBox root = new VBox();
    private HBox fila1 = new HBox();
    private HBox fila2 = new HBox();
    private HBox fila3 = new HBox();
    private HBox fila4 = new HBox();

    private Label lblFecha = new Label("Elige fecha");
    private DatePicker dtpFecha = new DatePicker();
    private Label lblPelicula = new Label("Elige película");
    private ComboBox <String> cmbPelicula = new ComboBox();
    private Label lblSala = new Label("Elige sala");
    private ComboBox <String> cmbSala = new ComboBox();
    private Label lblHora = new Label("Elige hora");
    private ComboBox <LocalTime> cmbHora = new ComboBox();
    private Button btnReserva = new Button("Reserva");
    private TicketServices ticketServices = new TicketServices();
    public ReservaScreen(Usuario usuario)  {
    configurarLayout();
    agregarComponentes();
    dtpFecha.setOnAction(event -> {
    if (dtpFecha.getValue() != null) {
        try {
            cmbPelicula.getItems().clear();
            cmbPelicula.getItems().addAll(ticketServices.cargarPeliculas(dtpFecha.getValue()));
            cmbPelicula.setDisable(false);
        } catch (Exception exception) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Películas no encontradas");
            alert.setTitle("Error al cargar películas");
            alert.setContentText(exception.getMessage());
            alert.showAndWait();
        }
    }

    });
        cmbPelicula.setOnAction(event -> {
            if (cmbPelicula.getValue() != null) {
                try {
                    cmbSala.getItems().clear();
                    cmbSala.getItems().addAll(ticketServices.cargarSalas(dtpFecha.getValue(),cmbPelicula.getValue()));
                    cmbSala.setDisable(false);
                } catch (Exception exception) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText("Salas no encontradas");
                    alert.setTitle("Error al cargar salas");
                    alert.setContentText(exception.getMessage());
                    alert.showAndWait();
                }
            }
        });
        cmbSala.setOnAction(event -> {
            if (cmbSala.getValue() != null) {
                try {
                    cmbHora.getItems().clear();
                    cmbHora.getItems().addAll(ticketServices.cargarHoras(dtpFecha.getValue(),cmbPelicula.getValue(),cmbSala.getValue()));
                    cmbHora.setDisable(false);
                } catch (Exception exception) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText("Horarios no encontrados");
                    alert.setTitle("Error al cargar horarios");
                    alert.setContentText(exception.getMessage());
                    alert.showAndWait();
                }
            }
        });
        cmbHora.setOnAction(event -> {
            if (cmbHora.getValue() != null) {
                btnReserva.setDisable(false);
            }
        });
        btnReserva.setOnAction(event -> {
            try {
                Sala sala = ticketServices.cargarButacas(dtpFecha.getValue(),cmbPelicula.getValue(),cmbSala.getValue(),cmbHora.getValue());

                Navigation.navigate("SalaScreen", sala, usuario);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
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
        cmbPelicula.setDisable(true);
        cmbSala.setDisable(true);
        cmbHora.setDisable(true);
        btnReserva.setDisable(true);
    }
}
