import models.Jugador;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.Scanner;

import static operations.Operation.*;

public class Main {
    public static void main(String[] args) {


        Scanner leer = new Scanner(System.in);





        int opcion;
        do {
            System.out.println("\n *********** CRUD FIFAS *********** ");
            System.out.println("1. Crear Jugador");
            System.out.println("2. Buscar Jugador");
            System.out.println("3. Mostrar jugadores");
            System.out.println("4. Cargar FC Barcelona 26-27");
            System.out.println("5. Borrar Jugador");
            System.out.println("6. Actualizar Dorsal de Jugador");
            System.out.println("7. Vaciar todos los datos (CSV)");
            System.out.println("8. Crear Técnico");
            System.out.println("9. Asignar Equipo a Jugador");
            System.out.println("10. Crear Equipo (con Técnico y Jugadores)");
            System.out.println("11. Cargar Real Madrid 26-27");
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
                case 5:
                    Scanner leerDelete = new Scanner(System.in);
                    System.out.println("Ingrese el nombre del jugador a borrar:");
                    operations.Operation.deletePlayer(leerDelete.nextLine());
                    break;
                case 6:
                    Scanner leerUpdate = new Scanner(System.in);
                    System.out.println("Ingrese el nombre del jugador a actualizar:");
                    String nombreUpdate = leerUpdate.nextLine();

                    System.out.println("Ingrese el nuevo número de dorsal:");
                    int nuevoDorsal = leerUpdate.nextInt();

                    operations.Operation.updatePlayer(nombreUpdate, nuevoDorsal);
                    break;
                case 7:
                    operations.Operation.limpiarTodosLosCSV();
                    break;
                case 8:
                    Scanner leerTec = new Scanner(System.in);
                    models.Tecnico nuevoTec = new models.Tecnico();
                    nuevoTec.setId(String.valueOf(new java.util.Random().nextInt(90) + 10));
                    System.out.println("Nombre del Técnico:");
                    nuevoTec.name = leerTec.nextLine();
                    System.out.println("Club que dirige (o 'Sin equipo'):");
                    nuevoTec.club = leerTec.nextLine();
                    operations.Operation.saveTecnico(nuevoTec);
                    break;

                case 9:
                    Scanner leerAsig = new Scanner(System.in);
                    System.out.println("Nombre del jugador:");
                    String jugAsig = leerAsig.nextLine();
                    System.out.println("Nombre del equipo a asignar:");
                    String eqAsig = leerAsig.nextLine();
                    operations.Operation.asignarEquipoAJugador(jugAsig, eqAsig);
                    break;
                case 10:
                    Scanner leerEq = new Scanner(System.in);
                    String idEq = String.valueOf(new java.util.Random().nextInt(90) + 10);
                    System.out.println("Nombre del nuevo Equipo:");
                    String nomEq = leerEq.nextLine();

                    models.Tecnico dtEq = new models.Tecnico();
                    dtEq.setId(String.valueOf(new java.util.Random().nextInt(90) + 10));
                    System.out.println("Nombre del Técnico para este equipo:");
                    dtEq.name = leerEq.nextLine();
                    dtEq.club = nomEq;
                    operations.Operation.saveTecnico(dtEq);

                    System.out.println("¿Cuántos jugadores desea vincular por ID?");
                    int cant = leerEq.nextInt();
                    leerEq.nextLine(); // Limpiar buffer
                    String[] ids = new String[cant];
                    for (int i = 0; i < cant; i++) {
                        System.out.println("Ingrese el ID del jugador " + (i + 1) + ":");
                        ids[i] = leerEq.nextLine();
                    }

                    models.Equipo eqNuevo = new models.Equipo(idEq, nomEq, dtEq, ids);
                    operations.Operation.saveTeam(eqNuevo);
                    break;

                case 11:
                    cargarRealMadrid();
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

        // 1. Creamos el arreglo vacío para guardar los IDs de los 11 jugadores
        String[] idsJugadores = new String[datosBarca.length];

        for (int i = 0; i < datosBarca.length; i++) {
            String idRandom = String.valueOf(rnd.nextInt(90) + 10);

            // 2. Guardamos el ID generado en nuestro arreglo
            idsJugadores[i] = idRandom;

            int anio = Integer.parseInt(datosBarca[i][1]);
            int mes = Integer.parseInt(datosBarca[i][2]);
            int dia = Integer.parseInt(datosBarca[i][3]);

            Jugador j = new Jugador(idRandom, datosBarca[i][0], LocalDate.of(anio, mes, dia));
            j.dorsal = i + 1;
            j.equipo = "FC Barcelona";

            operations.Operation.savePlayer(j);
        }
        System.out.println("¡Plantilla del Barça guardada en players.csv!");

        // 3. Crear al Técnico
        models.Tecnico dt = new models.Tecnico();
        dt.setId(String.valueOf(rnd.nextInt(90) + 10)); // ID aleatorio
        dt.name = "Hansi Flick";
        dt.club = "FC Barcelona";
        operations.Operation.saveTecnico(dt);

        // 4. Crear el Equipo y guardarlo
        // Le pasamos un ID fijo al equipo (ej. "1"), el nombre, el técnico y el arreglo de IDs
        models.Equipo equipo = new models.Equipo("1", "FC Barcelona", dt, idsJugadores);
        operations.Operation.saveTeam(equipo);
    }
    public static void cargarRealMadrid() {
        System.out.println("Cargando plantilla titular del Real Madrid 26-27...");

        // Formato: {Nombre, Año, Mes, Día, Dorsal}
        String[][] datosMadrid = {
                {"Thibaut Courtois", "1992", "5", "11", "1"},
                {"Trent Alexander-Arnold", "1998", "10", "7", "12"},
                {"Eder Militao", "1998", "1", "18", "3"},
                {"Dean Huijsen", "2005", "4", "14", "4"},
                {"Alvaro Carreras", "2003", "3", "23", "18"},
                {"Federico Valverde", "1998", "7", "22", "8"},
                {"Jude Bellingham", "2003", "6", "29", "5"},
                {"Arda Guler", "2005", "2", "25", "15"},
                {"Vinicius Jr", "2000", "7", "12", "7"},
                {"Kylian Mbappe", "1998", "12", "20", "10"},
                {"Yan Diomande", "2006", "11", "14", "25"}
        };

        java.util.Random rnd = new java.util.Random();
        String[] idsJugadores = new String[datosMadrid.length];

        for (int i = 0; i < datosMadrid.length; i++) {
            String idRandom = String.valueOf(rnd.nextInt(90) + 10);
            idsJugadores[i] = idRandom;

            int anio = Integer.parseInt(datosMadrid[i][1]);
            int mes = Integer.parseInt(datosMadrid[i][2]);
            int dia = Integer.parseInt(datosMadrid[i][3]);
            int dorsalReal = Integer.parseInt(datosMadrid[i][4]);

            models.Jugador j = new models.Jugador(idRandom, datosMadrid[i][0], java.time.LocalDate.of(anio, mes, dia));
            j.dorsal = dorsalReal;
            j.equipo = "Real Madrid";

            operations.Operation.savePlayer(j);
        }

        // Técnico del Real Madrid 26-27
        models.Tecnico dt = new models.Tecnico();
        dt.setId(String.valueOf(rnd.nextInt(90) + 10));
        dt.name = "Jose Mourinho";
        dt.club = "Real Madrid";
        operations.Operation.saveTecnico(dt);

        // Guardar Equipo en teams.csv
        models.Equipo equipo = new models.Equipo("2", "Real Madrid", dt, idsJugadores);
        operations.Operation.saveTeam(equipo);
    }
}
