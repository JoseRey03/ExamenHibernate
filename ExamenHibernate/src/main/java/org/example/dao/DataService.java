package org.example.dao;

import java.util.List;
import java.util.stream.Collectors;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.example.models.Opinion;
import org.example.models.Pelicula;

public class DataService {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void registrarPelicula(String titulo) {
        Pelicula pelicula = new Pelicula(titulo);
        em.persist(pelicula);
    }

    public List<Opinion> obtenerOpinionesPorUsuario(String correo) {
        return em.createQuery("SELECT o FROM Opinion o WHERE o.usuario = :correo", Opinion.class)
                .setParameter("correo", correo)
                .getResultList();
    }

    @Transactional
    public void añadirOpinionAPelicula(Long peliculaId, String descripcion, String usuario, int puntuacion) {
        Pelicula pelicula = em.find(Pelicula.class, peliculaId);
        if (pelicula != null) {
            Opinion opinion = new Opinion(descripcion, usuario, puntuacion);
            pelicula.añadirOpinion(opinion);
            em.persist(opinion);
        } else {
            throw new IllegalArgumentException("Pelicula no encontrada");
        }
    }

    public List<Pelicula> obtenerPeliculasConBajaPuntuacion() {
        return em.createQuery(
                        "SELECT DISTINCT p FROM Pelicula p JOIN p.opiniones o WHERE o.puntuacion <= 3", Pelicula.class)
                .getResultList();
    }

    public void setEntityManager(EntityManager em) {
    }
}