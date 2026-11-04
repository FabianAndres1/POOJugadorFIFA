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

                // data_readed[0] es ID, data_readed[1] es Nombre
                if (data_readed.length > 1 && data_readed[1].equalsIgnoreCase(name)) {
                    System.out.println(playerReader);
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
            // Leer y guardar todo en el ArrayList
            BufferedReader buffer = new BufferedReader(new FileReader("players.csv"));
            String linea;
            while ((linea = buffer.readLine()) != null) {
                String[] datos = linea.split(",");

                // Buscar por nombre (índice 1) y modificar estado is_alive (índice 4)
                if (datos.length > 4 && datos[1].equalsIgnoreCase(name)) {
                    datos[4] = "false";
                    linea = String.join(",", datos);
                    encontrado = true;
                    System.out.println("Jugador '" + name + "' eliminado (estado inactivo).");
                }
                lineas.add(linea);
            }
            buffer.close();

            // Sobrescribir el archivo si se encontró el jugador
            if (encontrado) {
                FileWriter escribir = new FileWriter("players.csv", false); // "false" borra y reescribe
                for (String l : lineas) {
                    escribir.write(l + "\n");
                }
                escribir.close();
            } else {
                System.out.println("Jugador no encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Error al borrar: " + e.getMessage());
        }
    }

    public static void updatePlayer(String name, int nuevoDorsal) {
        java.util.ArrayList<String> lineas = new java.util.ArrayList<>();
        boolean encontrado = false;

        try {
            // Leer el archivo y guardar en el ArrayList
            BufferedReader buffer = new BufferedReader(new FileReader("players.csv"));
            String linea;
            while ((linea = buffer.readLine()) != null) {
                String[] datos = linea.split(",");

                // Buscar por nombre (índice 1) y modificar el dorsal (índice 2)
                if (datos.length > 4 && datos[1].equalsIgnoreCase(name)) {
                    datos[2] = String.valueOf(nuevoDorsal);
                    linea = String.join(",", datos);
                    encontrado = true;
                    System.out.println("Jugador '" + name + "' actualizado con el dorsal " + nuevoDorsal + ".");
                }
                lineas.add(linea);
            }
            buffer.close();

            // Sobrescribir el archivo si hubo actualización
            if (encontrado) {
                FileWriter escribir = new FileWriter("players.csv", false);
                for (String l : lineas) {
                    escribir.write(l + "\n");
                }
                escribir.close();
            } else {
                System.out.println("Jugador no encontrado para actualizar.");
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar: " + e.getMessage());
        }
    }
        //TODO adicionar ID para los registros de jugadores


    }


















