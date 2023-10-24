package org.mf.catalogo.model;

import java.io.File;

public class Pelicula {
    private int idPelicula;
    private String titulo;
    private String url;
    private File imagen;
    private Genero genero;
    private String nombreGenero;

    public Pelicula(String titulo) {
        this.titulo = titulo;
    }

    public Pelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public Pelicula(String titulo, String url, Genero genero) {
        this.titulo = titulo;
        this.url = url;
        this.genero = genero;
    }

    public Pelicula(int idPelicula, String titulo, String url, Genero genero) {
        this.idPelicula = idPelicula;
        this.titulo = titulo;
        this.url = url;
        this.genero = genero;
    }

    public Pelicula(int idPelicula, String titulo, String url, File imagen, Genero genero) {
        this.idPelicula = idPelicula;
        this.titulo = titulo;
        this.url = url;
        this.imagen = imagen;
        this.genero = genero;
    }

    public Pelicula(String titulo, File imagen, Genero genero) {
        this.titulo = titulo;
        this.imagen = imagen;
        this.genero = genero;
    }

    public Pelicula(String titulo, Genero genero) {
        this.titulo = titulo;
        this.genero = genero;
    }

    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public File getImagen() {
        return imagen;
    }

    public void setImagen(File imagen) {
        this.imagen = imagen;
    }

    public String getNombreGenero() {
        return nombreGenero;
    }

    public void setNombreGenero(String nombreGenero) {
        this.nombreGenero = nombreGenero;
    }

    @Override
    public String toString() {
        return "id: " + this.idPelicula + " " + this.titulo + "|";
    }
}
