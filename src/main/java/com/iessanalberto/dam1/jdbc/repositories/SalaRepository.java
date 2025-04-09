package com.iessanalberto.dam1.jdbc.repositories;

import com.iessanalberto.dam1.jdbc.models.Butaca;
import javafx.util.Pair;

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
                "SELECT B.Fila,B.Numero_butaca, CASE WHEN R.User IS NOT NULL THEN TRUE ELSE FALSE END AS reservada FROM Realiza RL INNER JOIN Butaca B ON RL.Id = B.Id AND RL.Numero = B.Numero LEFT JOIN Reserva R ON B.Id = R.Id AND B.Numero = R.Numero AND B.Fila = R.Fila AND B.Numero_butaca = R.Numero_butaca AND RL.Id_proyeccion = R.Id_proyeccion WHERE RL.Id_proyeccion = ?;");
        preparedStatement.setInt(1,idProyeccion);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Butaca butaca = new Butaca (resultSet.getInt("Fila"),resultSet.getInt("Numero_butaca"),resultSet.getBoolean("reservada"));
            listaButacas.put(indiceMapa,butaca);
            indiceMapa++;
        }
        return listaButacas;
    }

    public void reservarButacas(int idSala, int numSala, ArrayList<Pair<Integer, Integer>> butacasReservadas, int idProyeccion, String user) throws Exception {
        try {
            PreparedStatement preparedStatement = ConnectionDB.connect().prepareStatement(
                    "INSERT INTO Reserva VALUES (?,?,?,?,?,?)");

        for (Pair pair:butacasReservadas) {
            preparedStatement.setInt(1,idSala);
            preparedStatement.setInt(2,numSala);
            preparedStatement.setInt(3, (Integer) pair.getKey());
            preparedStatement.setInt(4,(Integer) pair.getValue());
            preparedStatement.setInt(5,idProyeccion);
            preparedStatement.setString(6,user);
            preparedStatement.executeUpdate();

        }

    } catch (Exception exception) {
        throw new Exception("No se ha podido hacer la reserva");
        }
    }
}
