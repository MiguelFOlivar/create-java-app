package org.mf.catalogo.repositorio;

import java.sql.SQLException;
import java.util.List;

public interface RepositorioBuscar<T> {
    List<T> buscarT(String titulo) throws SQLException;
    List<T> buscarG(String genero) throws SQLException;
}
