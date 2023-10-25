package org.mf.catalogo.repositorio;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface Repositorio<T> {
    List<T>select() throws SQLException;
    void insert(T t) throws SQLException, FileNotFoundException;
    void update(Integer integer, String s) throws SQLException;
    default void update(T t) throws SQLException{};
    void delete(Integer integer) throws SQLException;
    void setConn(Connection conn);
}
