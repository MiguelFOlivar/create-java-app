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

public class PeliculaImpl implements Repositorio<Pelicula> {
    String updateQueryTitle = "UPDATE peliculas SET titulo=? WHERE id_pelicula=?";
    String updateQueryImg = "UPDATE peliculas SET img=? WHERE id_pelicula=?";
    String updateQueryUrl = "UPDATE peliculas SET url=? WHERE id_pelicula=?";
    String selecGenero = "SELECT * FROM generos";
    Connection conn;

    public PeliculaImpl(Connection conn) {
        this.conn = conn;
    }

    public PeliculaImpl() {
    }

    public Connection getConn() {
        return conn;
    }

    @Override
    public List<Pelicula> select() throws SQLException {
        conn = ConexionBD.getConexion();
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

    @Override
    public void insert(Pelicula pelicula) throws SQLException {
        String insertQuery = "INSERT INTO peliculas(titulo, url, imagen, genero_id)" +
                " VALUES(?, ?, ? ,(SELECT id_genero FROM generos WHERE genero= ?))";
        PreparedStatement ps = conn.prepareStatement(insertQuery);
        File img = pelicula.getImagen();

        try (FileInputStream fileInputStream = new FileInputStream(img);) {
            ps.setString(1, pelicula.getTitulo());
            ps.setString(2, pelicula.getUrl());
            ps.setBinaryStream(3, fileInputStream, (int) img.length());
            if(!genExists(pelicula.getNombreGenero())) {
                Genero genero = new Genero(pelicula.getNombreGenero());
                new GeneroImpl(conn).insert(genero);
            }
            ps.setString(4, pelicula.getNombreGenero());
            ps.executeUpdate();
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }

    }

    public boolean genExists(String genero) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(selecGenero);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            if(rs.getString("genero").equalsIgnoreCase(genero)) {
                return true;
            }
        }
        return false;
    }


    public void update(Integer id, String titulo) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(updateQueryTitle);
        ps.setInt(1, id);
        ps.setString(2, titulo);
        ps.executeUpdate();
    }
    @Override
    public void update(Pelicula pelicula) {

    }

    @Override
    public void delete(Integer id) throws SQLException {
        String deleteQuery = "DELETE FROM peliculas WHERE id_pelicula=?";
        PreparedStatement ps = conn.prepareStatement(deleteQuery);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    @Override
    public void setConn(Connection conn) {

    }
}
