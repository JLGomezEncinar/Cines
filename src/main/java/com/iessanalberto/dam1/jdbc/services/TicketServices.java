package com.iessanalberto.dam1.jdbc.services;


import com.iessanalberto.dam1.jdbc.models.Sala;
import com.iessanalberto.dam1.jdbc.repositories.TicketRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class TicketServices {
    private TicketRepository ticketRepository = new TicketRepository();
    public ArrayList<String> cargarPeliculas(LocalDate fecha) throws Exception {

        return ticketRepository.cargarPeliculas(fecha);
    }

    public ArrayList<String> cargarSalas(LocalDate fecha, String pelicula) throws Exception {
        return ticketRepository.cargarSalas(fecha,pelicula);
    }

    public ArrayList<LocalTime> cargarHoras(LocalDate fecha, String pelicula, String sala) throws Exception {
        return ticketRepository.cargarHoras(fecha,pelicula,sala);
    }

    public Sala cargarButacas(LocalDate fecha,String pelicula, String sala, LocalTime hora ) throws Exception {
        return ticketRepository.cargarButacas(fecha,pelicula,sala,hora);
    }
}
