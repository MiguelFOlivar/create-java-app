package org.mf.catalogo.utils;

import org.mf.catalogo.model.Pelicula;
import org.mf.catalogo.service.CatalogoServicio;
import org.mf.catalogo.service.Servicio;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MenuInicial {
    private static final Servicio servicio = new CatalogoServicio();

    public static void menu() throws SQLException {
        Scanner sc = new Scanner(System.in);
        String op;
        boolean salir = false;
        try {
            do{
            System.out.println("Ingresa una opcion:");
            System.out.println("1. Buscar por titulo \n2. Buscar por genero\n0. Salir...");
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
                            System.out.println("Ingresa el Id de la pelicula para mas detalles, 0 para rgresar al menú");
                            String selecion2 = sc.next();
                            if (Integer.parseInt(selecion2) == 0) {
                                esc2 = true;
                            }
                            for (Pelicula p : porGenero) {
                                if ((p.getIdPelicula() == Integer.parseInt(selecion2))) {
                                    System.out.println(p.mostrarDetalles());
                                    break;
                                }
                            }
                        }break;
                    }

                    break;
                default:
                    break;
            }
        }while(!salir);
        }catch (NumberFormatException e) {

        }
    }

}

