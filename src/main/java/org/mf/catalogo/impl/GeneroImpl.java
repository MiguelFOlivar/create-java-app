package org.mf.catalogo.impl;

import org.mf.catalogo.model.Genero;
import org.mf.catalogo.repositorio.Repositorio;
import org.mf.catalogo.utils.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GeneroImpl implements Repositorio<Genero> {
    private final String updateQuery = "UPDATE generos SET genero=? WHERE id_genero =?";
    Connection conn;

    public GeneroImpl() {
    }

    public Connection getConn() {
        return conn;
    }

    public GeneroImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Genero> select() throws SQLException {
        List<Genero> generos = new ArrayList<>();
        Genero genero = null;
        conn = ConexionBD.getConexion();
        String selectQuery = "SELECT * FROM generos";
        PreparedStatement ps = conn.prepareStatement(selectQuery);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            genero = new Genero();
            genero.setIdGenero(rs.getInt("id_genero"));
            genero.setNombreGenero(rs.getString("genero"));
            generos.add(genero);
        }
        return generos;
    }

    @Override
    public void insert(Genero genero) throws SQLException {
        String insertQuery = "INSERT INTO generos(genero) VALUES(?)";
        PreparedStatement ps = conn.prepareStatement(insertQuery);
        ps.setString(1, genero.getNombreGenero());
        ps.executeUpdate();
    }

    @Override
    public void update(Integer idGenero, String s) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(updateQuery);
        ps.setInt(1, idGenero);
        ps.executeUpdate();
    }

    @Override
    public void update(Genero genero) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(updateQuery);
        ps.setString(1, genero.getNombreGenero());
        ps.setInt(2, genero.getIdGenero());
        ps.executeUpdate();
    }

    @Override
    public void delete(Integer idGenero) throws SQLException {
        String deleteQuery = "DELETE FROM generos WHERE id_genero=?";
        PreparedStatement ps = conn.prepareStatement(deleteQuery);
        ps.setInt(1, idGenero);
        ps.executeUpdate();
    }

    @Override
    public void setConn(Connection conn) {

    }
}
