package com.iessanalberto.dam1.jdbc.navigation;

import com.iessanalberto.dam1.jdbc.screens.LoginScreen;
import com.iessanalberto.dam1.jdbc.screens.RegisterScreen;
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
                    Scene registerScene = new Scene(registerScreen.getRoot(),320,240);
                    stage.setTitle("Nuevo registro");
                    stage.setScene(registerScene);
                    stage.show();
            }

        }
    }
}



