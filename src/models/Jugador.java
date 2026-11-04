package models;

import java.time.LocalDate;
import java.time.Period;

public class Jugador extends Persona {
    public int dorsal;
    public Posicion posicion;
    private LocalDate nacimiento;

    public void setNacimiento(LocalDate nacimiento) {
        this.nacimiento = nacimiento;
    }

    public int getEdad()
    {
         return Period.between(
                nacimiento,
                LocalDate.now()
        ).getYears();

    }


    @Override
    public String toString() {
        return "models.Jugador{" +
                "dorsal=" + dorsal +
                "} "
                + super.toString();
    }


    public Jugador(String id, String nombre, LocalDate nacimiento) {
        super.setId(id);
        super.name = nombre;
        this.nacimiento = nacimiento;
    }

    public String playerToCSV() {
        return super.getId() + "," + this.name + "," + this.dorsal + "," + this.getEdad() + "," + this.is_alive;
    }}