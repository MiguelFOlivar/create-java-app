package org.mf.catalogo.impl;
import org.mf.catalogo.model.Genero;
import org.mf.catalogo.model.Pelicula;
import org.mf.catalogo.repositorio.RepositorioBuscar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BuscarPeliculaImpl implements RepositorioBuscar<Pelicula> {

    private String selectQuery;
    private Connection conn;


    public BuscarPeliculaImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Pelicula> buscarT(String titulo) throws SQLException {
        List<Pelicula> peliculas = new ArrayList<>();
        selectQuery = "SELECT * FROM peliculas p INNER JOIN generos g" +
                " ON p.genero_id=g.id_genero WHERE p.titulo LIKE" + "'%" + titulo + "%'";
        PreparedStatement ps = conn.prepareStatement(selectQuery);
        ResultSet rs = ps.executeQuery();
        Pelicula pelicula = null;
        while (rs.next()) {
            pelicula = new Pelicula();
            pelicula.setIdPelicula(rs.getInt("id_pelicula"));
            pelicula.setTitulo(rs.getString("titulo"));
            pelicula.setUrl(rs.getString("url"));
            pelicula.setNombreGenero(rs.getString("genero"));
            peliculas.add(pelicula);
        }
        return peliculas;
    }

    @Override
    public List<Pelicula> buscarG(String genero) throws SQLException {
        List<Pelicula> peliculas = new ArrayList<>();
        Pelicula pelicula = null;
        selectQuery = "SELECT * FROM peliculas INNER JOIN generos " +
                " ON peliculas.genero_id = generos.id_genero WHERE genero=?";
        PreparedStatement ps = conn.prepareStatement(selectQuery);
        ps.setString(1, genero);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            pelicula = new Pelicula();
            pelicula.setIdPelicula(rs.getInt("id_pelicula"));
            pelicula.setTitulo(rs.getString("titulo"));
            pelicula.setUrl(rs.getString("url"));
            pelicula.setNombreGenero(rs.getString("genero"));
            peliculas.add(pelicula);
        }
        return peliculas;
    }
}
