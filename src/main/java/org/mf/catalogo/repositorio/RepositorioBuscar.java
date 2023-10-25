package org.mf.catalogo.repositorio;

import java.sql.SQLException;
import java.util.List;

public interface RepositorioBuscar<T> {
    List<T> buscarT(String string) throws SQLException;
    List<T> buscarG(String string) throws SQLException;
}
