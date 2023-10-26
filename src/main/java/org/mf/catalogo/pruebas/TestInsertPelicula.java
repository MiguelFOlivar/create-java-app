package org.mf.catalogo.pruebas;

import org.mf.catalogo.service.CatalogoServicio;
import org.mf.catalogo.service.Servicio;

import java.sql.SQLException;

public class TestInsertPelicula {
    public static void main(String[] args) throws SQLException {

        Servicio servicio = new CatalogoServicio();
        servicio.listarPeliculas().forEach(System.out::println);
        System.out.println("=========================");


    }
}
