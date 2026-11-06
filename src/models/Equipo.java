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
        // Formatea el arreglo para que se guarde como "[12, 45, 88]" asegurado con comillas
        String idsArreglo = "\"" + Arrays.toString(jugadoresIds) + "\"";
        String nombreEntrenador = (entrenador != null) ? entrenador.name : "Sin Entrenador";

        return this.id + "," + this.nombre + "," + nombreEntrenador + "," + idsArreglo;
    }
}