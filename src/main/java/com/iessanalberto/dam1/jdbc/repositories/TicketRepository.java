package com.iessanalberto.dam1.jdbc.repositories;

import com.iessanalberto.dam1.jdbc.models.Sala;

import java.sql.*;
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
                "SELECT r.Numero FROM Realiza r INNER JOIN Proyeccion p ON p.Id=r.Id_proyeccion WHERE p.Fecha= ? AND r.Nombre_pelicula= ?");

        preparedStatement.setDate(1, Date.valueOf(fecha));
        preparedStatement.setString(2, pelicula);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            listaSalas.add(resultSet.getString("Numero"));
        }
        if (listaSalas.isEmpty()) {
            throw new Exception ("No hay salas para esta película");
        }
        return listaSalas;
    }

    public ArrayList<LocalTime> cargarHoras(LocalDate fecha, String pelicula, String sala) throws Exception {
        ArrayList<LocalTime> listaHoras = new ArrayList<>();
        PreparedStatement preparedStatement = ConnectionDB.connect().prepareStatement(
                "SELECT Hora FROM Proyeccion p INNER JOIN Realiza r ON p.Id=r.Id_proyeccion WHERE p.Fecha= ? AND r.Nombre_pelicula= ? AND r.Numero = ?");

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

    public Sala cargarButacas(LocalDate fecha, String pelicula, String sala, LocalTime hora) throws Exception {
        Sala sala1 = null;
        PreparedStatement preparedStatement = ConnectionDB.connect().prepareStatement(
                "SELECT r.Id,r.Numero,Numero_filas,Numero_columnas,p.Id as Id_proyeccion from Salas s INNER JOIN Realiza r on s.Id = r.Id INNER JOIN Proyeccion p ON p.Id=r.Id_proyeccion WHERE p.Fecha= ? AND r.Nombre_pelicula= ?  AND r.Numero = ? AND p.Hora= ?");


        preparedStatement.setDate(1, Date.valueOf(fecha));
        preparedStatement.setString(2, pelicula);
        preparedStatement.setString(3, sala);
        preparedStatement.setTime(4, Time.valueOf(hora));
        ResultSet resultSet = preparedStatement.executeQuery();

        if (!resultSet.next()) {
            throw new Exception ("Error al cargar la sala");
        } else
         {

             sala1 = new Sala (resultSet.getInt("Id"),resultSet.getInt("Numero"),resultSet.getInt("Numero_filas"),resultSet.getInt("Numero_columnas"),resultSet.getInt("Id_proyeccion"));
        }
        return sala1;
    }
}

