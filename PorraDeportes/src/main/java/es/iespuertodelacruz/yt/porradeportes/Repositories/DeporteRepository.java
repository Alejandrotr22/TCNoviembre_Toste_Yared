package es.iespuertodelacruz.yt.porradeportes.Repositories;

import es.iespuertodelacruz.yt.porradeportes.entities.Deporte;
import es.iespuertodelacruz.yt.porradeportes.entities.Evento;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;
import java.util.ArrayList;

public class DeporteRepository implements ICrud<Deporte,Integer> {

    EntityManagerFactory emf;

    public DeporteRepository(EntityManagerFactory emf){
        this.emf = emf;
    }

    @Override
    public Deporte save(Deporte object) {
        EntityManager em = emf.createEntityManager();
        Deporte deporte = null;
        try{
            em.getTransaction().begin();
            for (Evento e: object.getEventos()) {
                e.setIdDeporte(object);
            }
            em.persist(object);
            em.getTransaction().commit();
            deporte = new Deporte(object);
        }catch (RollbackException ex){
            em.close();
            return null;
        }
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
    public void delete(Integer id) {

    }

    @Override
    public ArrayList<Deporte> findAll() {
        return null;
    }
}
