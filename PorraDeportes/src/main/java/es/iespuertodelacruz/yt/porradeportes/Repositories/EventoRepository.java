package es.iespuertodelacruz.yt.porradeportes.Repositories;

import es.iespuertodelacruz.yt.porradeportes.entities.Evento;
import jdk.jfr.Event;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;

public class EventoRepository implements ICrud<Evento,Integer>{

    EntityManagerFactory emf;

    public EventoRepository(EntityManagerFactory emf){
        this.emf = emf;
    }
    @Override
    public Evento save(Evento object) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(object);
        Evento evento = new Evento(object);
        em.close();
        return evento;
    }

    @Override
    public Evento findByID(Integer id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Evento evento = em.find(Evento.class, id);
        em.getTransaction().commit();
        em.close();
        return evento;
    }

    @Override
    public void update(Evento object) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public ArrayList<Evento> findAll() {
        return null;
    }
}
