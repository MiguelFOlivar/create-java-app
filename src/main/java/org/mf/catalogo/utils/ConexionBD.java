package org.mf.catalogo.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static String url = "jdbc:mysql://localhost:3306/java_app?serverTimezone=UTC";
    private static String usuario = "root";
    private static String password = "admin";
    public static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(url,usuario, password);
    }
}
