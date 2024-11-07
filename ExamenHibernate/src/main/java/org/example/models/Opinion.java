package org.example.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
public class Opinion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;

    @Email
    private String usuario;

    @Min(0)
    @Max(10)
    private int puntuacion;

    @ManyToOne
    @JoinColumn(name = "pelicula_id", nullable = false)
    private Pelicula pelicula;

    public Opinion() {}

    public Opinion(String descripcion, String usuario, int puntuacion) {
        this.descripcion = descripcion;
        this.usuario = usuario;
        this.puntuacion = puntuacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    @Override
    public String toString() {
        return "Opinion{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", usuario='" + usuario + '\'' +
                ", puntuacion=" + puntuacion +
                '}';
    }
}
