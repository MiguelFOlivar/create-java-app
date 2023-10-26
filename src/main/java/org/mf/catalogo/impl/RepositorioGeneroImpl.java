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

public class RepositorioGeneroImpl implements Repositorio<Genero> {
    //private String updateQuery = "UPDATE generos SET genero=? WHERE id_genero =?";
    Connection conn;

    public RepositorioGeneroImpl() {
    }

    @Override
    public void setConn(Connection conn) {

    }

    public RepositorioGeneroImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Genero> select() throws SQLException {
        try (Connection conn = ConexionBD.getConexion()) {
            List<Genero> generos = new ArrayList<>();
            Genero genero = null;
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
    }

    @Override
    public void insert(Genero genero) throws SQLException {
        try (Connection conn = ConexionBD.getConexion()) {

            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            try {

                String insertQuery = "INSERT INTO generos(genero) VALUES(?)";
                PreparedStatement ps = conn.prepareStatement(insertQuery);
                ps.setString(1, genero.getNombreGenero());
                ps.executeUpdate();
                conn.commit();
            } catch (SQLException ex) {
                conn.rollback();
                System.out.println("Entramos a rollback... \n" + ex.getMessage());
                ex.printStackTrace(System.out);
            }
        }

    }

    @Override
    public void update(Integer idGenero, String nuevoNombre) throws SQLException {
        String updateQuery = "UPDATE generos SET genero=? WHERE id_genero =?";
        conn = ConexionBD.getConexion();
        if (conn.getAutoCommit())
            conn.setAutoCommit(false);
        try {
            PreparedStatement ps = conn.prepareStatement(updateQuery);
            ps.setString(1, nuevoNombre);
            ps.setInt(2, idGenero);
            ps.executeUpdate();
            conn.commit();
            System.out.println();
        } catch (SQLException e) {
            conn.rollback();
            System.out.println("Entramos a rollback..." + e.getMessage());
            e.printStackTrace(System.out);
        }

    }

    @Override
    public void update(Genero genero) throws SQLException {
        try (Connection conn = ConexionBD.getConexion();) {
            String updateQuery = "UPDATE generos SET genero=? WHERE id_genero =?";
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            try {
                PreparedStatement ps = conn.prepareStatement(updateQuery);
                ps.setString(1, genero.getNombreGenero());
                ps.setInt(2, genero.getIdGenero());
                ps.executeUpdate();
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                System.out.println("Entramos a rollback" + e.getMessage());
                e.printStackTrace(System.out);
            }
        }

    }

    @Override
    public void delete(Integer idGenero) throws SQLException {
        try (Connection conn = ConexionBD.getConexion();) {
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            try {
                String deleteQuery = "DELETE FROM generos WHERE id_genero=?";
                PreparedStatement ps = conn.prepareStatement(deleteQuery);
                ps.setInt(1, idGenero);
                ps.executeUpdate();
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace(System.out);
            }
        }
    }
}
