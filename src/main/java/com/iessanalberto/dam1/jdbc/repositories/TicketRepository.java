package com.iessanalberto.dam1.jdbc.repositories;

import com.iessanalberto.dam1.jdbc.models.Sala;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class TicketRepository {
    public ArrayList<String> cargarPeliculas (LocalDate fecha) throws Exception {
        ArrayList<String> listaPeliculas = new ArrayList<>();

            PreparedStatement preparedStatement = ConnectionDB.connect().prepareStatement(
                    "SELECT Nombre_pelicula FROM Realiza r INNER JOIN Proyeccion p ON p.Id=r.Id_proyeccion WHERE p.Fecha= ?");
            preparedStatement.setDate(1, Date.valueOf(fecha));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listaPeliculas.add(resultSet.getString("Nombre_pelicula"));
            }
        if (listaPeliculas.isEmpty()) {
            throw new Exception ("No hay películas para el día seleccionado");
        }
        return listaPeliculas;
    }

    public ArrayList<String> cargarSalas(LocalDate fecha, String pelicula) throws Exception {
        ArrayList<String> listaSalas = new ArrayList<>();
        PreparedStatement preparedStatement = ConnectionDB.connect().prepareStatement(
                "SELECT CONCAT (s.Numero,'-',Tipo) AS Sala FROM Salas s INNER JOIN Realiza r ON s.Id=r.Id INNER JOIN Proyeccion p ON p.Id=r.Id_proyeccion WHERE p.Fecha= ? AND r.Nombre_pelicula= ?");

        preparedStatement.setDate(1, Date.valueOf(fecha));
        preparedStatement.setString(2, pelicula);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            listaSalas.add(resultSet.getString("Sala"));
        }
        if (listaSalas.isEmpty()) {
            throw new Exception ("No hay salas para esta película");
        }
        return listaSalas;
    }

    public ArrayList<LocalTime> cargarHoras(LocalDate fecha, String pelicula, String sala) throws Exception {
        ArrayList<LocalTime> listaHoras = new ArrayList<>();
        PreparedStatement preparedStatement = ConnectionDB.connect().prepareStatement(
                "SELECT Hora FROM Proyeccion p INNER JOIN Realiza r ON p.Id=r.Id_proyeccion INNER JOIN Salas s on s.Id = r.Id WHERE p.Fecha= ? AND r.Nombre_pelicula= ? AND CONCAT (s.Numero,'-',Tipo) = ?");

        preparedStatement.setDate(1, Date.valueOf(fecha));
        preparedStatement.setString(2, pelicula);
        preparedStatement.setString(3, sala);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            listaHoras.add(resultSet.getTime("Hora").toLocalTime());
        }
        if (listaHoras.isEmpty()) {
            throw new Exception ("No hay horarios para esta película");
        }
        return listaHoras;
    }

    public Sala cargarButacas(String sala) throws Exception {
        Sala sala1 = null;
        PreparedStatement preparedStatement = ConnectionDB.connect().prepareStatement(
                "SELECT Numero_filas,Numero_columnas from Salas WHERE CONCAT (Numero,'-',Tipo) = ?");


        preparedStatement.setString(1, sala);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (!resultSet.next()) {
            sala1 = new Sala (resultSet.getInt("Numero_filas"),resultSet.getInt("Numero_columnas"));
        } else
         {
            throw new Exception ("Error al cargar la sala");
        }
        return sala1;
    }
}

