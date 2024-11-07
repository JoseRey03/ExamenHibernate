package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.dao.DataService;
import org.example.models.Opinion;
import org.example.models.Pelicula;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidadPersistencia");
        EntityManager em = emf.createEntityManager();

        DataService dataService = new DataService();
        dataService.setEntityManager(em);

        em.getTransaction().begin();
        try {

            System.out.println("Registrando películas...");
            dataService.registrarPelicula("El Padrino");
            dataService.registrarPelicula("Titanic");
            dataService.registrarPelicula("Sharknado");


            System.out.println("Añadiendo opiniones...");
            dataService.añadirOpinionAPelicula(1L, "Obra maestra del cine", "user1@example.com", 10);
            dataService.añadirOpinionAPelicula(2L, "Hermosa historia de amor", "user2@example.com", 8);
            dataService.añadirOpinionAPelicula(3L, "Ridículamente entretenida", "user3@example.com", 3);
            dataService.añadirOpinionAPelicula(3L, "Mala hasta decir basta", "user4@example.com", 1);


            System.out.println("Obteniendo opiniones de user3@example.com...");
            List<Opinion> opinionesUsuario = dataService.obtenerOpinionesPorUsuario("user3@example.com");
            opinionesUsuario.forEach(System.out::println);


            System.out.println("Obteniendo películas con baja puntuación...");
            List<Pelicula> peliculasBajaPuntuacion = dataService.obtenerPeliculasConBajaPuntuacion();
            peliculasBajaPuntuacion.forEach(System.out::println);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}

