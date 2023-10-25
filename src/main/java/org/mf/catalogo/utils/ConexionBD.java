package org.mf.catalogo.utils;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static String url = "jdbc:mysql://localhost:3306/java_app?serverTimezone=UTC";
    private static String usuario = "root";
    private static String password = "admin";
    private static BasicDataSource getPool() {
        BasicDataSource pool = new BasicDataSource();
        pool.setUrl(url);
        pool.setUsername(usuario);
        pool.setPassword(password);
        pool.setMinIdle(3);
        pool.setInitialSize(3);
        pool.setMaxIdle(8);
        return pool;
    }
    public static Connection getConexion() throws SQLException {
        return getPool().getConnection();
    }
}
