package com.iessanalberto.dam1.jdbc.navigation;

import com.iessanalberto.dam1.jdbc.models.Sala;
import com.iessanalberto.dam1.jdbc.models.Usuario;
import com.iessanalberto.dam1.jdbc.screens.LoginScreen;
import com.iessanalberto.dam1.jdbc.screens.RegisterScreen;
import com.iessanalberto.dam1.jdbc.screens.ReservaScreen;
import com.iessanalberto.dam1.jdbc.screens.SalaScreen;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Navigation {
    public static Stage stage = new Stage();

    public static void navigate(String destination) {
        switch (destination) {
            case "LoginScreen" -> {
                LoginScreen loginScreen = new LoginScreen();
                Scene loginScene = new Scene(loginScreen.getRoot(), 320, 240);
                stage.setTitle("Conexión");
                stage.setScene(loginScene);
                stage.show();
            }
            case "RegisterScreen" -> {
                RegisterScreen registerScreen = new RegisterScreen();
                Scene registerScene = new Scene(registerScreen.getRoot(), 320, 240);
                stage.setTitle("Nuevo registro");
                stage.setScene(registerScene);
                stage.show();
            }

        }
    }

    public static void navigate(String destination, Usuario usuario) {
        switch (destination) {
            case "ReservaScreen" -> {
                ReservaScreen reservaScreen = new ReservaScreen(usuario);
                Scene reservaScene = new Scene(reservaScreen.getRoot(), 320, 240);
                stage.setTitle("Reserva");
                stage.setScene(reservaScene);
                stage.show();
            }
        }
    }
    public static void navigate(String destination, Sala sala) {
        switch (destination) {
            case "SalaScreen" -> {
                SalaScreen salaScreen = new SalaScreen(sala);
                Scene salaScene = new Scene(salaScreen.getRoot(), 320, 240);
                stage.setTitle("Butacas");
                stage.setScene(salaScene);
                stage.show();
            }
        }
    }
}


