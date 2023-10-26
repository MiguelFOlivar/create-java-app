package org.mf.catalogo.pruebas;

import org.mf.catalogo.model.Pelicula;
import org.mf.catalogo.service.CatalogoServicio;
import org.mf.catalogo.service.Servicio;

import java.io.File;
import java.sql.SQLException;

public class TestComplete {
    public static void main(String[] args) throws SQLException {
        Servicio servicio = new CatalogoServicio();
       /* System.out.println("=====Listando peliculas=====");
        servicio.listarPeliculas().forEach(System.out::println);
        System.out.println("\n=====Listando generos=====");
        servicio.listarGeneros().forEach(System.out::println);
        System.out.println("\n=====Buscar por genero=====");
        servicio.buscarPorGenero("fantasia").forEach(System.out::println);
        System.out.println("\n=====Buscar por titulo=====");
        servicio.buscarPorTitulo("padrino").forEach(System.out::println);
        System.out.println("Fin de la consulta...");
        */
       /* Genero animacion = new Genero("Animación");
        servicio.insertarGenero(animacion);
        servicio.listarGeneros().forEach(System.out::println);*/

      /*  servicio.listarPeliculas().forEach(System.out::println);
        System.out.println("=====ELiminando pelicula=====");
        servicio.borrarPelicula(37);*/
        //servicio.listarPeliculas().forEach(System.out::println);
        /*System.out.println("=====Actualizando pelicula=====");
        servicio.actualizarPelicula(18, "One piece: La Pelicula");
        servicio.listarPeliculas().forEach(System.out::println);*/
        servicio.listarGeneros().forEach(System.out::println);
        servicio.eliminarGenero(46);
        System.out.println("\n====Actualizando peliculas=====");
        /*Genero novela = new Genero("Novela");*/
        /*servicio.insertarGenero(novela);*/
        File img = null;

        Pelicula pelicula = new Pelicula();
        img =new File("img/pez.jpg");


        pelicula.setTitulo("El gran Pez");
        pelicula.setUrl("https://www.imdb.com/title/tt0319061/");
        pelicula.setImagen(img);
        pelicula.setNombreGenero("La mejor pelicula del mundo");
        servicio.listarPeliculas().forEach(System.out::println);
        servicio.insertarPelicula(pelicula);
        servicio.listarPeliculas().forEach(System.out::println);
        servicio.listarGeneros().forEach(System.out::println);


    }
}
