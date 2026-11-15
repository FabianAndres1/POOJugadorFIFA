package operations;

import models.Jugador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

public class Operation {
    public static void savePlayer(Jugador jugadorToSave)
    {
    try{
        FileWriter escribir = new FileWriter("players.csv", true);

        //escribir.write(jugadorToSave.playerToCSV());
        escribir.write(jugadorToSave.playerToCSV());
        escribir.write("\n");
        escribir.close();
        System.out.println("Player Saved");

    }
    catch (IOException e)
    {
        System.out.println(e.getMessage());
    }
}
    public static void readPlayers()
    {
        try {
            FileReader lector = new FileReader("players.csv");
            BufferedReader buffer = new BufferedReader(lector);
            String playerReader;

            while ((playerReader= buffer.readLine())!=null)
            {
                System.out.println(playerReader);
            }


            buffer.close();
            System.out.println("All players have been read");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void readOnePlayer(String name) {
        try {
            FileReader lector = new FileReader("players.csv");
            BufferedReader buffer = new BufferedReader(lector);
            String playerReader;

            while ((playerReader = buffer.readLine()) != null) {
                String[] data_readed = playerReader.split(",");

                String[] datos = playerReader.split(",");
                if (datos.length > 5) {
                    String nombreLimpio = datos[1].replace("nombre:", "").trim();
                    if (nombreLimpio.equalsIgnoreCase(name)) {
                        System.out.println(playerReader);
                    }
                }
            }

            buffer.close();
            System.out.println("Búsqueda finalizada.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deletePlayer(String name) {
        java.util.ArrayList<String> lineas = new java.util.ArrayList<>();
        boolean encontrado = false;

        try {
            java.io.BufferedReader buffer = new java.io.BufferedReader(new java.io.FileReader("players.csv"));
            String linea;
            while ((linea = buffer.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length > 5) {
                    String nombreLimpio = datos[1].replace("nombre:", "").trim();
                    if (nombreLimpio.equalsIgnoreCase(name)) {
                        datos[5] = " estado: false";
                        linea = String.join(",", datos);
                        encontrado = true;
                        System.out.println("Jugador " + nombreLimpio + " marcado como inactivo (estado: false).");
                    }
                }
                lineas.add(linea);
            }
            buffer.close();

            if (encontrado) {
                java.io.FileWriter escribir = new java.io.FileWriter("players.csv", false);
                for (String l : lineas) {
                    escribir.write(l + "\n");
                }
                escribir.close();
            } else {
                System.out.println("Jugador no encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar jugador: " + e.getMessage());
        }
    }

    public static void updatePlayer(String name, int newDorsal) {
        java.util.ArrayList<String> lineas = new java.util.ArrayList<>();
        boolean encontrado = false;

        try {
            java.io.BufferedReader buffer = new java.io.BufferedReader(new java.io.FileReader("players.csv"));
            String linea;
            while ((linea = buffer.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length > 5) {
                    String nombreLimpio = datos[1].replace("nombre:", "").trim();
                    if (nombreLimpio.equalsIgnoreCase(name)) {
                        datos[2] = " dorsal: " + newDorsal;
                        linea = String.join(",", datos);
                        encontrado = true;
                        System.out.println("Dorsal de " + nombreLimpio + " actualizado a " + newDorsal);
                    }
                }
                lineas.add(linea);
            }
            buffer.close();

            if (encontrado) {
                java.io.FileWriter escribir = new java.io.FileWriter("players.csv", false);
                for (String l : lineas) {
                    escribir.write(l + "\n");
                }
                escribir.close();
            } else {
                System.out.println("Jugador no encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar dorsal: " + e.getMessage());
        }
    }
    public static void saveTeam(models.Equipo equipoToSave) {
        try {
            FileWriter escribir = new FileWriter("teams.csv", true);
            escribir.write(equipoToSave.equipoToCSV() + "\n");
            escribir.close();
            System.out.println("Equipo '" + equipoToSave.nombre + "' guardado exitosamente en teams.csv");
        } catch (Exception e) {
            System.out.println("Error al guardar equipo: " + e.getMessage());
        }
    }
        //TODO adicionar ID para los registros de jugadores

    public static void limpiarTodosLosCSV() {
        String[] archivos = {"players.csv", "teams.csv", "coaches.csv"};
        for (String archivo : archivos) {
            try {
                java.io.FileWriter fw = new java.io.FileWriter(archivo, false);
                fw.write("");
                fw.close();
            } catch (Exception e) {
                System.out.println("Error limpiando " + archivo + ": " + e.getMessage());
            }
        }
        System.out.println("Todos los archivos CSV (jugadores, equipos y técnicos) han sido vaciados.");
    }
    public static void saveTecnico(models.Tecnico tecnicoToSave) {
        try {
            java.io.FileWriter escribir = new java.io.FileWriter("coaches.csv", true);
            escribir.write(tecnicoToSave.tecnicoToCSV() + "\n");
            escribir.close();
            System.out.println("Técnico '" + tecnicoToSave.name + "' guardado en coaches.csv");
        } catch (Exception e) {
            System.out.println("Error al guardar técnico: " + e.getMessage());
        }
    }

    public static void asignarEquipoAJugador(String nombreJugador, String nombreEquipo) {
        java.util.ArrayList<String> lineas = new java.util.ArrayList<>();
        boolean encontrado = false;

        try {
            java.io.BufferedReader buffer = new java.io.BufferedReader(new java.io.FileReader("players.csv"));
            String linea;
            while ((linea = buffer.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length > 5) {
                    String nombreLimpio = datos[1].replace("nombre:", "").trim();
                    if (nombreLimpio.equalsIgnoreCase(nombreJugador)) {
                        datos[4] = " equipo: " + nombreEquipo;
                        linea = String.join(",", datos);
                        encontrado = true;
                        System.out.println("Equipo '" + nombreEquipo + "' asignado a " + nombreJugador);
                    }
                }
                lineas.add(linea);
            }
            buffer.close();

            if (encontrado) {
                java.io.FileWriter escribir = new java.io.FileWriter("players.csv", false);
                for (String l : lineas) {
                    escribir.write(l + "\n");
                }
                escribir.close();
            } else {
                System.out.println("Jugador no encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Error al asignar equipo: " + e.getMessage());
        }
    }
    }


















