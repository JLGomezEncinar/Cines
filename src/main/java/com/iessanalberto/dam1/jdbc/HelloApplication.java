package com.iessanalberto.dam1.jdbc;

import com.iessanalberto.dam1.jdbc.navigation.Navigation;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Navigation.navigate("LoginScreen");
    }

    public static void main(String[] args) {
        launch();
    }
}