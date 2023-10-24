package org.mf.catalogo.repositorio;

import org.mf.catalogo.model.Genero;
import org.mf.catalogo.model.Pelicula;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

public interface Repositorio<T> {
    List<T>select() throws SQLException;
    void insert(T t) throws SQLException, FileNotFoundException;
    void update(Integer integer) throws SQLException;

    void update(Genero genero) throws SQLException;

    void update(Pelicula pelicula);

    void delete(Integer integer) throws SQLException;
}
