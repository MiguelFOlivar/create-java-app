package org.mf.catalogo.pruebas;
import org.mf.catalogo.impl.GeneroImpl;
import org.mf.catalogo.impl.PeliculaImpl;
import org.mf.catalogo.model.Genero;
import org.mf.catalogo.model.Pelicula;
import org.mf.catalogo.repositorio.Repositorio;
import org.mf.catalogo.utils.ConexionBD;

import java.sql.Connection;
import java.sql.SQLException;

public class TestCatalogo {
    public static void main(String[] args) throws SQLException {
        Connection conn = null;


        try {
            conn = ConexionBD.getConexion();
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }

            Repositorio<Genero> repoGenero = new GeneroImpl(conn);
            repoGenero.select().forEach(System.out::println);
            //repoGenero.insert(new Genero("Histórica"));
            repoGenero.select().forEach(System.out::println);
            System.out.println("======== |Actualizando Genero| ========");
            repoGenero.update(new Genero(4, "Acción"));
            repoGenero.select().forEach(System.out::println);
            System.out.println("======== |Eliminando Genero| ========");
            repoGenero.delete(9);
            repoGenero.select().forEach(System.out::println);
            conn.commit();

        } catch (SQLException e) {
            assert conn != null;
            conn.rollback();
            System.out.println("Entramos a Rollback");
            System.out.println(e.getMessage());
        }
    }
}
