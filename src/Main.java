import models.Jugador;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.Scanner;

import static operations.Operation.*;

public class Main {
    public static void main(String[] args) {

        //models.Jugador newPlayer = new models.Jugador("Pele", LocalDate.of(1940, 10,23));
        //newPlayer.is_alive  =  false; 12
        //newPlayer.dorsal=10;
        //TODO crear menu para usar y operar las acciones de los jugadores

        Scanner leer = new Scanner(System.in);


        //Menu
        //Ingresar jugador -> pulsa 1
        //Leer todos jugadores -> pulsa 2
        //Buscar jugador -> pulsa 3


        int opcion;
        do {
            System.out.println("\n *********** CRUD FIFAS *********** ");
            System.out.println("1. Crear Jugador");
            System.out.println("2. Buscar Jugador");
            System.out.println("3. Mostrar jugadores");
            System.out.println("4. Cargar FC Barcelona 26-27");
            //TODO actualizar y borrar
            System.out.println("0. Salir destamonda");
            System.out.println("*********** CRUD FIFAS *********** ");

            opcion = leer.nextInt();

            switch (opcion) {
                case 1:
                    newPlayer();
                    break;
                case 2:
                    findPlayer();
                    break;
                case 3:
                    showAllPlayers();
                    break;
                case 4:
                    cargarBarcelona();
                    break;
                case 0:
                    System.out.println("Nos vemos");
                    break;
                default:
                    System.out.println("Abrite burro");


            }
        }
        while (opcion!=0);

    }

    public static void newPlayer() {
        Scanner leer = new Scanner(System.in);
        System.out.println("Ingrese el nombre del jugador");
        String name_player = leer.nextLine();
        System.out.println("Ingrese año de nacimiento");
        int year = leer.nextInt();
        System.out.println("Ingrese mes de naciemiento");
        int month = leer.nextInt();
        System.out.println("Ingrese día de nacimiento");
        int day = leer.nextInt();

        // 1. Generamos un ID aleatorio de 2 dígitos
        java.util.Random rnd = new java.util.Random();
        String idRandom = String.valueOf(rnd.nextInt(90) + 10);

        // 2. Lo pasamos como primer argumento al constructor (id, nombre, fecha)
        savePlayer(new Jugador(idRandom, name_player, LocalDate.of(year, month, day)));
    }

    public static void findPlayer() {
        Scanner leer = new Scanner(System.in);
        System.out.println("Ingrese el jugador a buscar");
        readOnePlayer(leer.next());

    }

    public static void showAllPlayers() {
        readPlayers();

    }

    public static void cargarBarcelona() {
        System.out.println("Cargando titulares del FC Barcelona 26-27...");

        String[][] datosBarca = {
                {"Joan Garcia", "2001", "5", "4"},
                {"Xavi Espart", "2007", "1", "1"},
                {"Pau Cubarsi", "2007", "1", "22"},
                {"Gerard Martin", "2002", "2", "26"},
                {"Joao Cancelo", "1994", "5", "27"},
                {"Rodri", "1996", "6", "22"},
                {"Pedri", "2002", "11", "25"},
                {"Fermin Lopez", "2003", "5", "11"},
                {"Lamine Yamal", "2007", "7", "13"},
                {"Anthony Gordon", "2001", "2", "24"},
                {"Raphinha (C)", "1996", "12", "14"}
        };

        java.util.Random rnd = new java.util.Random();

        for (int i = 0; i < datosBarca.length; i++) {
            String idRandom = String.valueOf(rnd.nextInt(90) + 10);

            int anio = Integer.parseInt(datosBarca[i][1]);
            int mes = Integer.parseInt(datosBarca[i][2]);
            int dia = Integer.parseInt(datosBarca[i][3]);

            Jugador j = new Jugador(idRandom, datosBarca[i][0], LocalDate.of(anio, mes, dia));
            j.dorsal = i + 1;

            operations.Operation.savePlayer(j);
        }
        System.out.println("¡Plantilla del Barça guardada en CSV!");
    }

}
