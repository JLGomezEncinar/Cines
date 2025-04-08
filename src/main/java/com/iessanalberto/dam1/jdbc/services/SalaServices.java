package com.iessanalberto.dam1.jdbc.services;

import com.iessanalberto.dam1.jdbc.models.Butaca;
import com.iessanalberto.dam1.jdbc.repositories.SalaRepository;

import java.util.ArrayList;
import java.util.Map;

public class SalaServices {
    private SalaRepository salaRepository = new SalaRepository();

    public Map<Integer,Butaca> comprobarDisponibilidad(int idSala, int numSala, int idProyeccion) throws Exception {
        return salaRepository.comprobarDisponibilidad(idSala,numSala,idProyeccion);
    }
}
