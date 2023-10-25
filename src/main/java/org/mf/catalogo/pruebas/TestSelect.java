package org.mf.catalogo.pruebas;
import org.mf.catalogo.impl.PeliculaImpl;
import org.mf.catalogo.model.Pelicula;
import org.mf.catalogo.repositorio.Repositorio;
import org.mf.catalogo.utils.ConexionBD;
import java.sql.Connection;
import java.sql.SQLException;

public class TestSelect {
    public static void main(String[] args) {
        try(Connection conn = ConexionBD.getConexion()) {
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            try{

                Repositorio<Pelicula> peliculaRepositorio = new PeliculaImpl(conn);
                peliculaRepositorio.select().forEach(System.out::println);
                conn.commit();
            }catch (SQLException ex) {
                conn.rollback();
                System.out.println("Entramos a rollback..." + ex.getMessage());
                ex.printStackTrace(System.out);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
