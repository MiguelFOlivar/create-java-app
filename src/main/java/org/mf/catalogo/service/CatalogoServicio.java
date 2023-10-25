package org.mf.catalogo.service;
import org.mf.catalogo.impl.PeliculaImpl;
import org.mf.catalogo.model.Genero;
import org.mf.catalogo.model.Pelicula;
import org.mf.catalogo.repositorio.Repositorio;
import org.mf.catalogo.utils.ConexionBD;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CatalogoServicio implements Servicio {
    private Repositorio<Pelicula> peliculaRepositorio;
    private Repositorio<Genero> generoRepositorio;

    public CatalogoServicio() {
        this.peliculaRepositorio = new PeliculaImpl();
    }

    @Override
    public List<Pelicula> listarPeliculas() throws SQLException {
        try(Connection conn = ConexionBD.getConexion()) {
            this.peliculaRepositorio.setConn(conn);
            return peliculaRepositorio.select();
        }

    }

    @Override
    public void insertarPelicula(Pelicula pelicula) throws SQLException, FileNotFoundException {
        try(Connection conn = ConexionBD.getConexion()) {
            this.peliculaRepositorio.setConn(conn);
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }

            try{
                conn.commit();
            }catch (SQLException e){
                conn.rollback();
                e.printStackTrace(System.out);
            }
        }
    }

    @Override
    public void actualizarPelicula(Integer integer, String s) throws SQLException {
        try(Connection conn = ConexionBD.getConexion()) {
            this.peliculaRepositorio.setConn(conn);
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }

            try{
                conn.commit();
            }catch (SQLException e){
                conn.rollback();
                e.printStackTrace(System.out);
            }
        }
    }

    @Override
    public void borrarPelicula(Integer integer) throws SQLException {
        try(Connection conn = ConexionBD.getConexion()) {
            this.peliculaRepositorio.setConn(conn);
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }

            try{
                conn.commit();
            }catch (SQLException e){
                conn.rollback();
                e.printStackTrace(System.out);
            }
        }
    }

    @Override
    public List<Genero> listarGeneros() throws SQLException {
        try(Connection conn = ConexionBD.getConexion()) {
            this.generoRepositorio.setConn(conn);
        }
        return null;
    }

    @Override
    public void insertarGenero(Genero t) throws SQLException, FileNotFoundException {
        try(Connection conn = ConexionBD.getConexion()) {
            this.generoRepositorio.setConn(conn);
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }

            try{
                conn.commit();
            }catch (SQLException e){
                conn.rollback();
                e.printStackTrace(System.out);
            }
        }
    }

    @Override
    public void actualizarGenero(Integer integer, String s) throws SQLException {
        try(Connection conn = ConexionBD.getConexion()) {
            this.generoRepositorio.setConn(conn);
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }

            try{
                conn.commit();
            }catch (SQLException e){
                conn.rollback();
                e.printStackTrace(System.out);
            }
        }
    }

    @Override
    public void borrarGenero(Integer integer) throws SQLException {
        try(Connection conn = ConexionBD.getConexion()) {
            this.generoRepositorio.setConn(conn);
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }

            try{
                conn.commit();
            }catch (SQLException e){
                conn.rollback();
                e.printStackTrace(System.out);
            }
        }
    }
}
