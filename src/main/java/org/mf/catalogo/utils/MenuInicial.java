package org.mf.catalogo.utils;

import org.mf.catalogo.model.Genero;
import org.mf.catalogo.model.Pelicula;
import org.mf.catalogo.service.CatalogoServicio;
import org.mf.catalogo.service.Servicio;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static org.mf.catalogo.utils.DemoCatalogo.demoPeliculas;

public class MenuInicial {
    private static final Servicio servicio = new CatalogoServicio();
    private static int i = 1;

    public static void menu() throws SQLException {
        boolean salir = false;
        System.out.println("\t===========   Bienvenido  ===========");
        System.out.println("            Generos Disponibles\n");
        for (Genero g : servicio.listarGeneros()) {
            graficar(g);
        }
        if (!servicio.listarPeliculas().isEmpty()) {
            System.out.println("            Peliculas Disponibles\n");
            for (Pelicula p : servicio.listarPeliculas()) {
                graficar(p);
            }
        } else {
            System.out.println("\n\n" +
                    "***** No hay peliculas disponibles en la base de datos*****");
            //System.out.println("Ejecuta el metodo demoPeliculas (***solo 1 vez***).");
            System.out.println("Presiona 1 para ejecutar un demo de muestra.");
            Scanner sc = new Scanner(System.in);
            int demo = sc.nextInt();
            if (demo == 1) {
                demoPeliculas();
            }
        }
        System.out.println();
        Scanner sc = new Scanner(System.in);
        String op;
        try {
            do {
                System.out.println("\n\t\t\tIngresa una opcion:");
                System.out.println("1. Buscar por titulo    2. Buscar por genero    0. Salir...");
                op = sc.next();
                if (Integer.parseInt(op) == 0) {
                    salir = true;
                }

                switch (Integer.parseInt(op)) {
                    case 1:
                        System.out.print("Ingresa el titulo a buscar: ");
                        List<Pelicula> porTitulo = servicio.buscarPorTitulo(sc.next());
                        boolean esc1 = false;
                        porTitulo.forEach(System.out::println);
                        while (!esc1) {
                            if (!porTitulo.isEmpty()) {
                                esc1 = isEsc1(sc, porTitulo, esc1);
                            } else {
                                System.out.println("=== sin resultados para mostrar ===");
                                break;
                            }

                        }
                        break;
                    case 2:
                        boolean esc2 = false;
                        System.out.print("Ingresa el genero a buscar: ");
                        List<Pelicula> porGenero = servicio.buscarPorGenero(sc.next());
                        porGenero.forEach(System.out::println);
                        while (!esc2) {

                            if (!porGenero.isEmpty()) {
                                esc2 = isEsc1(sc, porGenero, esc2);
                            } else {
                                System.out.println("=== Sin resultados para mostrar ===");
                                break;
                            }
                        }

                        break;

                    default:
                        break;
                }
            } while (!salir);
        } catch (NumberFormatException e) {

        }
    }

    private static <T> void graficar(T t) {
        if (t instanceof Genero) {
            System.out.print(((Genero) t).getNombreGenero() + " | ");
        } else {
            System.out.print(((Pelicula) t).getTitulo() + " | ");
        }
        if (i % 5 == 0) {
            System.out.println("\n-----------------------------------------------------------");
        }
        i++;
    }

    private static boolean isEsc1(Scanner sc, List<Pelicula> porTitulo, boolean esc1) {
        System.out.println("Ingresa el Id de la pelicula para mas detalles, 0 para rgresar al menú");
        String selecion = sc.next();
        if (Integer.parseInt(selecion) == 0) {
            esc1 = true;
        }
        for (Pelicula p : porTitulo) {
            if ((p.getIdPelicula() == Integer.parseInt(selecion))) {
                System.out.println(p.mostrarDetalles());
                break;
            }

        }
        return esc1;
    }

}

