package es.iespuertodelacruz.yt.porradeportes.Repositories;

import es.iespuertodelacruz.yt.porradeportes.entities.Apuesta;
import es.iespuertodelacruz.yt.porradeportes.entities.Evento;
import es.iespuertodelacruz.yt.porradeportes.entities.Usuario;
import jdk.jfr.Event;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;
import java.util.ArrayList;
import java.util.List;

public class EventoRepository implements ICrud<Evento,Integer>{

    EntityManagerFactory emf;

    public EventoRepository(EntityManagerFactory emf){
        this.emf = emf;
    }
    @Override
    public Evento save(Evento object) {
        EntityManager em = emf.createEntityManager();
        Evento evento = null;
        try {
            em.getTransaction().begin();

            for (Apuesta a: object.getApuestas()) {
                a.setEvento(object);
            }

            em.persist(object);
            evento = new Evento(object);

            em.close();
        }catch (RollbackException ex){
            em.close();
            return null;
        }

        return evento;
    }

    @Override
    public Evento findByID(Integer id) {
        EntityManager em = emf.createEntityManager();
        Evento evento = null;
        try{
            em.getTransaction().begin();
            evento = em.find(Evento.class, id);
            em.getTransaction().commit();
            em.close();
        }catch (RollbackException ex){
            em.close();
            return null;
        }

        return evento;
    }

    @Override
    public Boolean update(Evento object) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Evento eventoBBDD = em.find(Evento.class, object.getId());
            eventoBBDD = new Evento(object);

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
        try {
            em.getTransaction().begin();
            Evento evento = em.find(Evento.class, id);
            if (evento != null){
                if (evento.getApuestas().size() == 0){
                    em.remove(evento);
                    em.getTransaction().commit();
                    em.close();
                    return true;

                }else{
                    em.close();
                    return false;
                }
            }else{
                em.close();
                return false;
            }

        }catch (RollbackException ex){
            em.close();
            return null;
        }
    }

    @Override
    public List<Evento> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Evento> eventos = new ArrayList<>();
        try {
            em.getTransaction().begin();
            eventos = em.createNamedQuery("Evento.findAll", Evento.class).getResultList();
            em.getTransaction().commit();
            em.close();
        }catch (RollbackException ex){

        }
        return eventos;
    }
}
