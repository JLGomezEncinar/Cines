package com.iessanalberto.dam1.jdbc.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;

public class LoginRepository {
    public String login(String user, String password) throws Exception {
        PreparedStatement preparedStatement = ConnectionDB.connect().prepareStatement(
                "SELECT NOMBRE FROM Usuario WHERE user = ? and password = ?");
        preparedStatement.setString(1, user);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (!resultSet.next()) {
            throw new Exception("Usuario/contraseña no encontrados");
        }
        return resultSet.getString("nombre");
    }


    public void register(String name, String user, String password) throws Exception {
        try {
            PreparedStatement preparedStatement = ConnectionDB.connect().prepareStatement(
                    "INSERT INTO Usuario VALUES (?,?,?)");

            preparedStatement.setString(1, user);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, name);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception exception) {
            throw new Exception ("El usuario ya existe en la base de datos");
        }





    }

}