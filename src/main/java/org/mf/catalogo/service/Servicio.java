package org.mf.catalogo.service;
import org.mf.catalogo.model.Genero;
import org.mf.catalogo.model.Pelicula;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

public interface Servicio {
    List<Pelicula>listarPeliculas() throws SQLException;
    void insertarPelicula(Pelicula pelicula) throws SQLException, FileNotFoundException;
    void actualizarPelicula(Integer integer, String s) throws SQLException;
    void borrarPelicula(Integer integer) throws SQLException;
    List<Genero>listarGeneros() throws SQLException;
    void insertarGenero(Genero t) throws SQLException, FileNotFoundException;
    void actualizarGenero(Integer integer, String s) throws SQLException;
    void borrarGenero(Integer integer) throws SQLException;

}
