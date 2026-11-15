package models;

import java.time.LocalDate;
import java.time.Period;

public class Jugador extends Persona {
    public int dorsal;
    public String equipo = "Sin equipo";
    private LocalDate nacimiento;

    public void setNacimiento(LocalDate nacimiento) {
        this.nacimiento = nacimiento;
    }

    public int getEdad() {
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
        return "id: " + getId() +
                ", nombre: " + this.name +
                ", dorsal: " + this.dorsal +
                ", edad: " + getEdad() +
                ", equipo: " + this.equipo +
                ", estado: " + this.is_alive;
    }
}