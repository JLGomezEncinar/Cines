package com.iessanalberto.dam1.jdbc.repositories;

import com.iessanalberto.dam1.jdbc.models.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;

public class LoginRepository {
    public Usuario login(String user, String password) throws Exception {
        Usuario usuario = null;
        PreparedStatement preparedStatement = ConnectionDB.connect().prepareStatement(
                "SELECT * FROM Usuario WHERE user = ? and password = ?");
        preparedStatement.setString(1, user);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (!resultSet.next()) {
            throw new Exception("Usuario/contraseña no encontrados");
        }
        usuario = new Usuario(resultSet.getString("nombre"), resultSet.getString("user"), resultSet.getString("password"));
        return usuario;
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