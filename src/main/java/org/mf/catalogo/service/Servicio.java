package org.mf.catalogo.service;
import org.mf.catalogo.model.Genero;
import org.mf.catalogo.model.Pelicula;

import java.sql.SQLException;
import java.util.List;

public interface Servicio {
    List<Pelicula>listarPeliculas() throws SQLException;
    void insertarPelicula(Pelicula pelicula) throws SQLException;
    void actualizarPelicula(Integer integer, String s) throws SQLException;
    void eliminarPelicula(Integer integer) throws SQLException;
    List<Genero>listarGeneros() throws SQLException;
    void insertarGenero(Genero t) throws SQLException;
    void actualizarGenero(Integer integer, String s) throws SQLException;
    void eliminarGenero(Integer integer) throws SQLException;
    public List<Pelicula> buscarPorTitulo(String titulo) throws SQLException;
    public List<Pelicula> buscarPorGenero(String genero) throws SQLException;

}
