package org.mf.catalogo.impl;
import org.mf.catalogo.model.Genero;
import org.mf.catalogo.model.Pelicula;
import org.mf.catalogo.repositorio.Repositorio;
import org.mf.catalogo.utils.ConexionBD;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepositorioPeliculaImpl implements Repositorio<Pelicula> {
    Connection conn;

    public RepositorioPeliculaImpl(Connection conn) {
        this.conn = conn;
    }

    public RepositorioPeliculaImpl() {
    }

    @Override
    public void setConn(Connection conn) {

    }

    @Override
    public List<Pelicula> select() throws SQLException {
        try (Connection conn = ConexionBD.getConexion()) {
            List<Pelicula> listaPeliculas = new ArrayList<>();
            Pelicula pelicula = null;
            Genero genero = null;
            String selectQuery = "SELECT * FROM peliculas p INNER JOIN generos g ON p.genero_id=g.id_genero";
            PreparedStatement ps = conn.prepareStatement(selectQuery);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                genero = new Genero(rs.getInt("id_genero"), rs.getString("genero"));
                pelicula = new Pelicula(rs.getString("titulo"));
                pelicula.setIdPelicula(rs.getInt("id_pelicula"));
                //pelicula.setTitulo(rs.getString("titulo"));
                pelicula.setUrl(rs.getString("url"));
                pelicula.setGenero(genero);
                listaPeliculas.add(pelicula);
            }
            return listaPeliculas;
        }

    }

    @Override
    public void insert(Pelicula pelicula) throws SQLException {
        try (Connection conn = ConexionBD.getConexion()) {
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            String insertQuery = "INSERT INTO peliculas(titulo, url, imagen, genero_id)" +
                    " VALUES(?, ?, ? ,(SELECT id_genero FROM generos WHERE genero= ?))";
            File img = pelicula.getImagen();
            try (FileInputStream fileInputStream = new FileInputStream(img);) {
                try {
                    PreparedStatement ps = conn.prepareStatement(insertQuery);
                    ps.setString(1, pelicula.getTitulo());
                    ps.setString(2, pelicula.getUrl());
                    ps.setBinaryStream(3, fileInputStream, (int) img.length());

                    if (!(genExists(pelicula.getNombreGenero()))) {
                        Genero genero = new Genero(pelicula.getNombreGenero());
                        new RepositorioGeneroImpl(conn).insert(genero);
                    }
                    ps.setString(4, pelicula.getNombreGenero());
                    ps.executeUpdate();
                    conn.commit();
                } catch (SQLException e) {
                    conn.rollback();
                    System.err.println("Rollback... |   " + e.getMessage());
                    //e.printStackTrace(System.out);
                }
            } catch (IOException e) {
                conn.rollback();
                /*System.out.println("Rollback insertando imagen...\n" + e.getMessage());*/
                System.err.println("Rollback... |   " + e.getMessage());
                e.printStackTrace(System.out);
            }
        }
    }

    private boolean genExists(String genero) throws SQLException {
        try (Connection conn = ConexionBD.getConexion()) {
            String selectGenero = "SELECT genero FROM generos";
            PreparedStatement ps = conn.prepareStatement(selectGenero);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("genero").equals(genero)) {
                    return true;
                }
            }
            return false;
        }
    }


    public void update(Integer id, String titulo) throws SQLException {
        try (Connection conn = ConexionBD.getConexion()) {
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            try {
                String updateQueryTitle = "UPDATE peliculas SET titulo=? WHERE id_pelicula=?";
                PreparedStatement ps = conn.prepareStatement(updateQueryTitle);
                ps.setString(1, titulo);
                ps.setInt(2, id);
                ps.executeUpdate();
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace(System.out);
            }
        }
    }

    @Override
    public void update(Pelicula pelicula) {
        //###TODO
    }

    @Override
    public void delete(Integer id) throws SQLException {
        try (Connection conn = ConexionBD.getConexion()) {
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            try {
                String deleteQuery = "DELETE FROM peliculas WHERE id_pelicula=?";
                PreparedStatement ps = conn.prepareStatement(deleteQuery);
                ps.setInt(1, id);
                ps.executeUpdate();
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace(System.out);
            }
        }
    }
}
