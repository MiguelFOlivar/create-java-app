package org.mf.catalogo.impl;

import org.mf.catalogo.model.Genero;
import org.mf.catalogo.model.Pelicula;
import org.mf.catalogo.repositorio.Repositorio;
import org.mf.catalogo.utils.ConexionBD;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PeliculaImpl implements Repositorio<Pelicula> {

    Connection conn;

    public PeliculaImpl(Connection conn) {
        this.conn = conn;
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
            genero = new Genero(rs.getInt("id_genero"), rs.getString("nombre"));
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
            ps.setString(4, pelicula.getNombreGenero());
            ps.executeUpdate();
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }

    }

    @Override
    public void update(Integer integer) throws SQLException {

    }

    @Override
    public void update(Genero genero) throws SQLException {

    }

    @Override
    public void update(Pelicula pelicula) {

    }

    @Override
    public void delete(Integer integer) {

    }
}
