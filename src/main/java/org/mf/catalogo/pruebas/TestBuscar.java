package org.mf.catalogo.pruebas;
import org.mf.catalogo.impl.BuscarPeliculaImpl;
import org.mf.catalogo.model.Pelicula;
import org.mf.catalogo.repositorio.RepositorioBuscar;
import org.mf.catalogo.utils.ConexionBD;
import java.sql.Connection;
import java.sql.SQLException;

public class TestBuscar {
    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        conn = ConexionBD.getConexion();
        if (conn.getAutoCommit()) {
            conn.setAutoCommit(false);
        }

        try {
            RepositorioBuscar<Pelicula> repositorioBuscar = new BuscarPeliculaImpl(conn);
            System.out.println("=====Buscar por titulo=====");
            repositorioBuscar.buscarT("spid").forEach(System.out::println);
            System.out.println();
            System.out.println("=====Buscar por Genero=====");
            repositorioBuscar.buscarG("Drama").forEach(System.out::println);
            conn.commit();
        }catch (SQLException e) {
            conn.rollback();
            System.out.println("Entramos a rollback..." + e.getMessage());
            e.printStackTrace(System.out);
        }
    }
}
