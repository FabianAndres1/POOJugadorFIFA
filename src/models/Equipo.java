package models;

import java.util.Arrays;

public class Equipo {
    public String id;
    public String nombre;
    public Tecnico entrenador;
    public String[] jugadoresIds;

    public Equipo(String id, String nombre, Tecnico entrenador, String[] jugadoresIds) {
        this.id = id;
        this.nombre = nombre;
        this.entrenador = entrenador;
        this.jugadoresIds = jugadoresIds;
    }

    public String equipoToCSV() {
        String idsArreglo = java.util.Arrays.toString(jugadoresIds);
        String nombreEntrenador = (entrenador != null) ? entrenador.name : "Sin Entrenador";
        return "id: " + this.id + ", nombre: " + this.nombre + ", tecnico: " + nombreEntrenador + ", jugadores: " + idsArreglo;
    }
}