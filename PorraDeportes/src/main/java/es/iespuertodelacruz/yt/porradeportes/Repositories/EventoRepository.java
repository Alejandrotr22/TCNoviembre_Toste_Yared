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
                a.setIdEvento(object);
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
    public void update(Evento object) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Evento eventoBBDD = em.find(Evento.class, object.getId());
            eventoBBDD = new Evento(object);
            em.getTransaction().commit();
            em.close();
            //return true;
        }catch (RollbackException ex){
            em.close();
            //return false;
        }

    }

    @Override
    public void delete(Integer id) {

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Evento evento = em.find(Evento.class, id);
            if (evento != null){
                if (evento.getApuestas().size() == 0){
                    em.remove(evento);
                    em.getTransaction().commit();
                    //return true;

                }else{
                    //return false;
                }
            }else{
                //return false;
            }
            em.close();
        }catch (RollbackException ex){
            em.close();
            //return false;
        }
    }

    @Override
    public ArrayList<Evento> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            List<Evento> eventos = new ArrayList<>();
            em.getTransaction().begin();
            eventos = em.createNamedQuery("Evento.findAll", Evento.class).getResultList();
            em.getTransaction().commit();
            em.close();
        }catch (RollbackException ex){

        }

        return eventos;
    }
}
