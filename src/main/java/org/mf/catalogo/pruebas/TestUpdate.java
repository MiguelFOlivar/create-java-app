package org.mf.catalogo.pruebas;

import org.mf.catalogo.impl.PeliculaImpl;
import org.mf.catalogo.model.Pelicula;
import org.mf.catalogo.repositorio.Repositorio;
import org.mf.catalogo.utils.ConexionBD;

import java.sql.Connection;
import java.sql.SQLException;

public class TestUpdate {
    public static void main(String[] args) {

        Connection conn = null;
        try {
            conn = ConexionBD.getConexion();
            if(conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            Repositorio<Pelicula> peliculaRepositorio = new PeliculaImpl(conn);
            peliculaRepositorio.select().forEach(System.out::println);
            System.out.println("Actualizando pelicula");
            peliculaRepositorio.update(6, "Thor: Ragnarok");
            peliculaRepositorio.select().forEach(System.out::println);

            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println("Entramos al Rollback --> " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
