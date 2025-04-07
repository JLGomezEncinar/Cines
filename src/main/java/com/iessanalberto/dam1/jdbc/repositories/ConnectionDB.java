package com.iessanalberto.dam1.jdbc.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private final static String url ="jdbc:mysql://localhost:3309/Cines?serverTimezone=UTC";
    private final static String username = "root";
    private final static String password = "root";
    private static Connection connection = null;

    public static Connection connect() throws Exception {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(url,username,password);
            } catch (Exception e) {
                throw new Exception("Error al conectar con la base de datos");
            }
        }
        return connection;
    }
}
