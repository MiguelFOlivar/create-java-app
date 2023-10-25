package org.mf.catalogo.pruebas;

import org.mf.catalogo.impl.PeliculaImpl;
import org.mf.catalogo.model.Pelicula;
import org.mf.catalogo.repositorio.Repositorio;
import org.mf.catalogo.utils.ConexionBD;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;

public class TestInsert {
    public static void main(String[] args) throws FileNotFoundException {
        Connection conn = null;
        try {
            conn = ConexionBD.getConexion();
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            Repositorio<Pelicula> repoPeliculas = new PeliculaImpl(conn);
            Pelicula nuevaPelicula = new Pelicula("Spider-man 2");
            //nuevaPelicula.setUrl("https://www.imdb.com/title/tt0993846/");
            nuevaPelicula.setUrl("https://www.imdb.com/title/tt0145487/?ref_=nv_sr_srsg_1_tt_7_nm_1_q_spiderman");
            nuevaPelicula.setImagen(new File("img/sp2.jpg"));
            nuevaPelicula.setNombreGenero("Acción");
            repoPeliculas.insert(nuevaPelicula);

            conn.commit();
        } catch (SQLException e) {
            try {
                assert conn != null;
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println("Entramos a rollback..." + e.getMessage());
            e.printStackTrace(System.out);
        }

    }
}
