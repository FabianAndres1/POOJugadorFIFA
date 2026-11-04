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

        //TODO adicionar ID para los registros de jugadores


    }


















