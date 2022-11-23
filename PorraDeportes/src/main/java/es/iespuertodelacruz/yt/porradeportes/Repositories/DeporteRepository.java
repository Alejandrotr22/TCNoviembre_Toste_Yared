package es.iespuertodelacruz.yt.porradeportes.Repositories;

import es.iespuertodelacruz.yt.porradeportes.entities.Deporte;
import es.iespuertodelacruz.yt.porradeportes.entities.Evento;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;
import java.util.ArrayList;
import java.util.List;

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
        Deporte deporte = null;
        try{
            em.getTransaction().begin();
            deporte = em.find(Deporte.class, id);
            em.getTransaction().commit();
            em.close();
        }catch (RollbackException ex){
            em.close();
            return null;
        }

        return deporte;
    }

    @Override
    public Boolean update(Deporte object) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Deporte deporteBBDD = em.find(Deporte.class, object.getId());
            deporteBBDD = new Deporte(object);
            em.getTransaction().commit();
            em.close();
            return true;
        }catch (RollbackException ex){
            em.close();
            return null;
        }
    }

    @Override
    public Boolean delete(Integer id) {
        EntityManager em = emf.createEntityManager();
        Boolean res = false;
        try {
            em.getTransaction().begin();
            Deporte deporte = em.find(Deporte.class, id);
            if (deporte != null){
                if (deporte.getEventos().size() == 0){
                    em.remove(deporte);
                    em.getTransaction().commit();
                    res = true;

                }else{
                    res = null;
                }
            }else{
                res =  false;
            }
            em.close();
        }catch (RollbackException ex){
            em.close();
            return res ;
        }
        return res ;
    }

    @Override
    public List<Deporte> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Deporte> deportes = new ArrayList<>();
        try {
            em.getTransaction().begin();
            deportes = em.createNamedQuery("Deporte.findAll", Deporte.class).getResultList();
            em.getTransaction().commit();
            em.close();
        }catch (RollbackException ex){
            return null;
        }
        return deportes;
    }
}
