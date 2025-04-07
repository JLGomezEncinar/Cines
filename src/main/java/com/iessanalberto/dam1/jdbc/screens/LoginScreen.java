package com.iessanalberto.dam1.jdbc.screens;

import com.iessanalberto.dam1.jdbc.navigation.Navigation;
import com.iessanalberto.dam1.jdbc.services.LoginServices;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LoginScreen {
    private VBox root = new VBox();
    private HBox fila1 = new HBox();
    private HBox fila2 = new HBox();
    //Componentes de la ventana
    private Label lblUsuario = new Label("Usuario");
    private TextField txtUsuario = new TextField();
    private Label lblPassword = new Label("Contraseña");
    private PasswordField txtPassword = new PasswordField();
    private Button btnConectar = new Button("Conectar");
    private Button btnRegistrar = new Button ("Registro");

    LoginServices loginServices = new LoginServices();
    public LoginScreen() {
        //Configuramos los elementos del layout
        root.setPadding(new Insets(10));
        root.setSpacing(20);
        root.setAlignment(Pos.CENTER);
        fila1.setAlignment(Pos.CENTER_RIGHT);
        fila1.setPadding(new Insets(0, 20, 0, 0));
        fila1.setSpacing(5);
        fila2.setAlignment(Pos.CENTER_RIGHT);
        fila2.setPadding(new Insets(0, 20, 0, 0));
        fila2.setSpacing(5);

        //Añadimos los componentes a su layout
        fila1.getChildren().addAll(lblUsuario, txtUsuario);
        fila2.getChildren().addAll(lblPassword, txtPassword);
        root.getChildren().addAll(fila1, fila2, btnConectar,btnRegistrar);
        btnConectar.setOnAction(actionEvent -> {
            try {
                String nombre = loginServices.login(txtUsuario.getText(), txtPassword.getText());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Conectado");
                alert.setTitle("Bienvenido al otro lado");
                alert.setContentText("Bienvenido señor/a " +nombre);
                alert.showAndWait();
            } catch (Exception exception) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Conexión no realizada");
                alert.setTitle("Error de conexión");
                alert.setContentText(exception.getMessage());
                alert.showAndWait();
            }

        });
        btnRegistrar.setOnAction(actionEvent -> {
            Navigation.navigate("RegisterScreen");
        });
    }
    public VBox getRoot() {
        return root;
    }
}
