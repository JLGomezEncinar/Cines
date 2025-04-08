package com.iessanalberto.dam1.jdbc.repositories;

import com.iessanalberto.dam1.jdbc.models.Butaca;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SalaRepository {
    public Map<Integer,Butaca> comprobarDisponibilidad(int idSala, int numSala, int idProyeccion) throws Exception {
        Map<Integer,Butaca> listaButacas = new HashMap<>();
        int indiceMapa = 1;
        PreparedStatement preparedStatement = ConnectionDB.connect().prepareStatement(
                "SELECT DISTINCT b.Fila, b.Numero_butaca, CASE WHEN r.Fila IS NULL THEN FALSE ELSE TRUE END AS reservada FROM Butaca b LEFT JOIN Reserva r ON b.Fila = r.Fila AND r.Numero_butaca=b.Numero_butaca;");

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Butaca butaca = new Butaca (resultSet.getInt("Fila"),resultSet.getInt("Numero_butaca"),resultSet.getBoolean("reservada"));
            listaButacas.put(indiceMapa,butaca);
            indiceMapa++;
        }
        return listaButacas;
    }
}
