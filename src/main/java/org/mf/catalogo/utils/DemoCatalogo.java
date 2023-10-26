package org.mf.catalogo.utils;

import org.mf.catalogo.model.Pelicula;
import org.mf.catalogo.service.CatalogoServicio;
import org.mf.catalogo.service.Servicio;

import java.io.File;
import java.sql.SQLException;

public class DemoCatalogo {
    public static void demoPeliculas() throws SQLException {
        //Quitar los comentarios para insertar peliculas de prueba
        File img = null;

        Pelicula pelicula2 = new Pelicula();
        Pelicula pelicula3 = new Pelicula();
        Pelicula pelicula4 = new Pelicula();
        Pelicula pelicula5 = new Pelicula();
        Pelicula pelicula6 = new Pelicula();
        Pelicula pelicula7 = new Pelicula();
        Pelicula pelicula8 = new Pelicula();

        Servicio servicio = new CatalogoServicio();


        Pelicula pelicula1 = new Pelicula();
        System.out.println("\n===== Ingresando Peliculas de Prueba  =====");
        img = new File("img/cadena.jpg");
        pelicula1 = new Pelicula();
        pelicula1.setTitulo("Cadena Perpetua");
        pelicula1.setUrl("https://www.imdb.com/title/tt0111161/");
        pelicula1.setImagen(img);
        pelicula1.setNombreGenero("Drama");
        servicio.insertarPelicula(pelicula1);

        img = new File("img/pez.jpg");
        pelicula2.setTitulo("El gran Pez");
        pelicula2.setUrl("https://www.imdb.com/title/tt0319061/");
        pelicula2.setImagen(img);
        pelicula2.setNombreGenero("Fantasia");
        servicio.insertarPelicula(pelicula2);

        img = new File("img/forma.jpg");
        pelicula3.setTitulo("La forma del agua");
        pelicula3.setUrl("https://www.imdb.com/title/tt5580390/");
        pelicula3.setImagen(img);
        pelicula3.setNombreGenero("Fantasía");
        servicio.insertarPelicula(pelicula3);

        img = new File("img/spiderwick.jpg");
        pelicula4.setTitulo("Las crónicas de Spiderwick");
        pelicula4.setUrl("https://www.imdb.com/title/tt0416236/");
        pelicula4.setImagen(img);
        pelicula4.setNombreGenero("Fantasía");
        servicio.insertarPelicula(pelicula4);


        img = new File("img/redfilm.jpg");
        pelicula5.setTitulo("One Piece: Red film");
        pelicula5.setUrl("https://www.imdb.com/title/tt11737520/");
        pelicula5.setImagen(img);
        pelicula5.setNombreGenero("Fantasía");
        servicio.insertarPelicula(pelicula5);

        img = new File("img/sp2.jpg");
        pelicula6.setTitulo("Spider-man 2");
        pelicula6.setUrl("https://www.imdb.com/title/tt0145487/");
        pelicula6.setImagen(img);
        pelicula6.setNombreGenero("Ciencia Ficcion");
        servicio.insertarPelicula(pelicula6);

        img = new File("img/ironman3.jpg");
        pelicula7.setTitulo("Iron man 3");
        pelicula7.setUrl("https://www.imdb.com/title/tt1300854/");
        pelicula7.setImagen(img);
        pelicula7.setNombreGenero("Aventura");
        servicio.insertarPelicula(pelicula7);

        img = new File("img/corleone.jpg");
        pelicula8.setTitulo("EL padrino");
        pelicula8.setUrl("https://www.imdb.com/title/tt0068646/");
        pelicula8.setImagen(img);
        pelicula8.setNombreGenero("Drama");
        servicio.insertarPelicula(pelicula8);

        img = new File("img/corleone.jpg");
        pelicula8.setTitulo("EL padrino 2");
        pelicula8.setUrl("https://www.imdb.com/title/tt0068646/");
        pelicula8.setImagen(img);
        pelicula8.setNombreGenero("Drama");
        servicio.insertarPelicula(pelicula8);



    }
}
