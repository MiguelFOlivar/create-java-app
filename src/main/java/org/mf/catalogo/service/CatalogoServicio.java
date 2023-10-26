package org.mf.catalogo.service;
import org.mf.catalogo.impl.BuscarPeliculaImpl;
import org.mf.catalogo.impl.RepositorioGeneroImpl;
import org.mf.catalogo.impl.RepositorioPeliculaImpl;
import org.mf.catalogo.model.Genero;
import org.mf.catalogo.model.Pelicula;
import org.mf.catalogo.repositorio.Repositorio;
import org.mf.catalogo.utils.ConexionBD;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CatalogoServicio implements Servicio {
    private final Repositorio<Pelicula> peliculaRepositorio;
    private final Repositorio<Genero> generoRepositorio;
    private final BuscarPeliculaImpl buscarRepositorio;

    public CatalogoServicio() {
        this.peliculaRepositorio = new RepositorioPeliculaImpl();
        this.generoRepositorio = new RepositorioGeneroImpl();
        this.buscarRepositorio = new BuscarPeliculaImpl();
    }

    @Override
    public List<Pelicula> listarPeliculas() throws SQLException {
        try (Connection conn = ConexionBD.getConexion()) {
            this.peliculaRepositorio.setConn(conn);
            return peliculaRepositorio.select();
        }

    }

    @Override
    public void insertarPelicula(Pelicula pelicula) throws SQLException {
        try (Connection conn = ConexionBD.getConexion()) {
            this.peliculaRepositorio.setConn(conn);
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            try {
                peliculaRepositorio.insert(pelicula);
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace(System.out);
            }
        }
    }

    @Override
    public void actualizarPelicula(Integer id, String titulo) throws SQLException {
        try (Connection conn = ConexionBD.getConexion()) {
            this.peliculaRepositorio.setConn(conn);
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }

            try {
                peliculaRepositorio.update(id, titulo);
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace(System.out);
            }
        }
    }

    @Override
    public void eliminarPelicula(Integer id) throws SQLException {
        try (Connection conn = ConexionBD.getConexion()) {
            this.peliculaRepositorio.setConn(conn);
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }

            try {
                peliculaRepositorio.delete(id);
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace(System.out);
            }
        }
    }

    @Override
    public List<Genero> listarGeneros() throws SQLException {
        try (Connection conn = ConexionBD.getConexion()) {
            this.generoRepositorio.setConn(conn);
            return generoRepositorio.select();
        }

    }

    @Override
    public void insertarGenero(Genero genero) throws SQLException {
        try (Connection conn = ConexionBD.getConexion()) {
            this.generoRepositorio.setConn(conn);
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }

            try {
                generoRepositorio.insert(genero);
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace(System.out);
            }
        }
    }

    @Override
    public void actualizarGenero(Integer id, String nombre) throws SQLException {
        try (Connection conn = ConexionBD.getConexion()) {
            this.generoRepositorio.setConn(conn);
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            try {
                generoRepositorio.update(id, nombre);
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                System.out.println("entramos a rollback..." + e.getMessage());
                e.printStackTrace(System.out);
            }
        }
    }

    @Override
    public void eliminarGenero(Integer id) throws SQLException {
        try (Connection conn = ConexionBD.getConexion()) {
            this.generoRepositorio.setConn(conn);
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }

            try {
                generoRepositorio.delete(id);
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace(System.out);
            }
        }
    }

    @Override
    public List<Pelicula> buscarPorTitulo(String titulo) throws SQLException {
        try (Connection conn = ConexionBD.getConexion()) {
            this.buscarRepositorio.setConn(conn);
            return this.buscarRepositorio.buscarT(titulo);
        }
    }

    @Override
    public List<Pelicula> buscarPorGenero(String genero) throws SQLException {
        try (Connection conn = ConexionBD.getConexion()) {
            this.buscarRepositorio.setConn(conn);
            return this.buscarRepositorio.buscarG(genero);
        }
    }



}
