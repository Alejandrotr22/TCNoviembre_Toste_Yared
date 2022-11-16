package es.iespuertodelacruz.yt.porradeportes.Repositories;

import es.iespuertodelacruz.yt.porradeportes.entities.Deporte;
import es.iespuertodelacruz.yt.porradeportes.entities.Evento;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;

public class DeporteRepository implements ICrud<Deporte,Integer> {

    EntityManagerFactory emf;

    public DeporteRepository(EntityManagerFactory emf){
        this.emf = emf;
    }

    @Override
    public Deporte save(Deporte object) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(object);
        em.getTransaction().commit();
        Deporte deporte = new Deporte(object);
        em.close();
        return deporte;
    }

    @Override
    public Deporte findByID(Integer id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Deporte deporte = em.find(Deporte.class, id);
        em.getTransaction().commit();
        em.close();
        return deporte;
    }

    @Override
    public void update(Deporte object) {

    }

    @Override
    public void delete(Deporte object) {

    }

    @Override
    public ArrayList<Deporte> findAll() {
        return null;
    }
}
