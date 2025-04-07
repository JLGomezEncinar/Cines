package com.iessanalberto.dam1.jdbc.screens;

import com.iessanalberto.dam1.jdbc.navigation.Navigation;
import com.iessanalberto.dam1.jdbc.services.LoginServices;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.SQLIntegrityConstraintViolationException;

public class RegisterScreen {
private VBox root = new VBox();

    private HBox fila1 = new HBox();
    private HBox fila2 = new HBox();
    private HBox fila3 = new HBox();
    private HBox fila4 = new HBox();
    private HBox fila5 = new HBox();
    //Componentes de la ventana
    private Label lblNombre = new Label("Nombre");
    private TextField txtNombre = new TextField();
    private Label lblUsuario = new Label("Usuario");
    private TextField txtUsuario = new TextField();
    private Label lblPassword = new Label("Contraseña");
    private PasswordField txtPassword = new PasswordField();
    private Label lblRepeat = new Label("Repite Contraseña");
    private PasswordField txtRepeat = new PasswordField();
    private Button btnVolver = new Button ("Volver");
    private Button btnRegistrar = new Button ("Registro");
    LoginServices loginServices = new LoginServices();
public RegisterScreen() {
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
    fila5.setAlignment(Pos.CENTER_RIGHT);
    fila5.setPadding(new Insets(0, 20, 0, 0));
    fila5.setSpacing(5);

    //Añadimos los componentes a su layout
    fila1.getChildren().addAll(lblNombre, txtNombre);
    fila2.getChildren().addAll(lblUsuario, txtUsuario);
    fila3.getChildren().addAll(lblPassword, txtPassword);
    fila4.getChildren().addAll(lblRepeat, txtRepeat);
    fila5.getChildren().addAll(btnVolver, btnRegistrar);
    root.getChildren().addAll(fila1, fila2, fila3,fila4,fila5);
    btnVolver.setOnAction(actionEvent -> Navigation.navigate("LoginScreen"));
    btnRegistrar.setOnAction(actionEvent -> {
        try {
            loginServices.register(txtNombre.getText(),txtUsuario.getText(),txtPassword.getText(),txtRepeat.getText());
            Alert alert = new Alert (Alert.AlertType.INFORMATION);
            alert.setHeaderText("");
            alert.setTitle("");
            alert.setContentText("El usuario se ha registrado correctamente");
            alert.showAndWait();
         } catch (Exception e) {
            Alert alert = new Alert (Alert.AlertType.WARNING);
            alert.setHeaderText("");
            alert.setTitle("");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    });
}
    public VBox getRoot() {
        return root;
    }
}
