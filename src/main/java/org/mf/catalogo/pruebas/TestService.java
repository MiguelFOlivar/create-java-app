package org.mf.catalogo.pruebas;

import org.mf.catalogo.model.Genero;
import org.mf.catalogo.model.Pelicula;
import org.mf.catalogo.service.CatalogoServicio;
import org.mf.catalogo.service.Servicio;

import java.io.File;
import java.sql.SQLException;

public class TestService {

    public static void main(String[] args) throws SQLException {

        Servicio servicio = new CatalogoServicio();

        System.out.println("=====Listando Peliculas=====");
        servicio.listarPeliculas().forEach(System.out::println);

        System.out.println("\n=====Listando generos=====");
        servicio.listarGeneros().forEach(System.out::println);

        System.out.println("=====Buscando pelicula por genero=====");
        servicio.buscarPorGenero("suspenso").forEach(System.out::println);

        System.out.println("\n=====Buscando pelicula por titulo=====");
        servicio.buscarPorTitulo("thor").forEach(System.out::println);

        File imagen = null;
        Pelicula peliculaNueva = new Pelicula();
        /*System.out.println("\n=====Ingresando nueva pelicula=====");
        imagen = new File("img/cadena.jpg");
        peliculaNueva = new Pelicula();
        peliculaNueva.setTitulo("Cadena Perpetua");
        peliculaNueva.setUrl("https://www.imdb.com/title/tt0111161/");
        peliculaNueva.setImagen(imagen);
        peliculaNueva.setNombreGenero("Drama");
        servicio.insertarPelicula(peliculaNueva);*/

       /* System.out.println("\n=====Ingresando nueva pelicula=====");
        imagen = new File("img/forma.jpg");
        peliculaNueva = new Pelicula();
        peliculaNueva.setTitulo("La forma del agua");
        peliculaNueva.setUrl("https://www.imdb.com/title/tt5580390/");
        peliculaNueva.setImagen(imagen);
        peliculaNueva.setNombreGenero("Fantasía");
        servicio.insertarPelicula(peliculaNueva);*/

       /* imagen = new File("img/spiderwick.jpg");
        peliculaNueva.setTitulo("Las crónicas de Spiderwick");
        peliculaNueva.setUrl("https://www.imdb.com/title/tt0416236/");
        peliculaNueva.setImagen(imagen);
        peliculaNueva.setNombreGenero("Fantasía");
        servicio.insertarPelicula(peliculaNueva);*/

        /*imagen = new File("img/redfilm.jpg");
        peliculaNueva.setTitulo("One Piece: Red film");
        peliculaNueva.setUrl("https://www.imdb.com/title/tt11737520/");
        peliculaNueva.setImagen(imagen);
        peliculaNueva.setNombreGenero("Fantasía");
        servicio.insertarPelicula(peliculaNueva);*/

//        imagen = new File("img/sp2.jpg");
//        peliculaNueva.setTitulo("Spider-man 2");
//        peliculaNueva.setUrl("https://www.imdb.com/title/tt0145487/");
//        peliculaNueva.setImagen(imagen);
//        peliculaNueva.setNombreGenero("Ciencia Ficcion");
//        servicio.insertarPelicula(peliculaNueva);


        imagen = new File("img/ironman3.jpg");
        peliculaNueva.setTitulo("Iron man 3");
        peliculaNueva.setUrl("https://www.imdb.com/title/tt1300854/");
        peliculaNueva.setImagen(imagen);
        peliculaNueva.setNombreGenero("Aventura");
        servicio.insertarPelicula(peliculaNueva);
        System.out.println("\n=====Listando Peliculas=====");
        servicio.listarPeliculas().forEach(System.out::println);
        servicio.listarGeneros();

    }
}
