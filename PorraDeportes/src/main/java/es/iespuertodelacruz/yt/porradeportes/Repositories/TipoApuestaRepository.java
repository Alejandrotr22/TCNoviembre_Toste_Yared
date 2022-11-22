package es.iespuertodelacruz.yt.porradeportes.Repositories;

import es.iespuertodelacruz.yt.porradeportes.entities.Apuesta;
import es.iespuertodelacruz.yt.porradeportes.entities.TipoApuesta;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;
import java.util.List;

public class TipoApuestaRepository implements ICrud<TipoApuesta, Integer> {

    EntityManagerFactory emf;

    public TipoApuestaRepository(EntityManagerFactory emf){ this.emf = emf;}


    @Override
    public TipoApuesta save(TipoApuesta object) {

        TipoApuesta tipoApuesta = null;

        EntityManager em = emf.createEntityManager();

        try{

            em.getTransaction().begin();

            for (Apuesta apuesta : object.getApuestas()) {
                apuesta.setTipoApuesta(object);
            }

            em.persist(object);
            em.getTransaction().commit();
            tipoApuesta = new TipoApuesta(object);

        }catch (RollbackException ex){
            em.close();
            return null;
        }

        em.close();
        return tipoApuesta;

    }

    @Override
    public TipoApuesta findByID(Integer id) {

        EntityManager em = emf.createEntityManager();
        TipoApuesta tipoApuesta;
        try {

            tipoApuesta = em.find(TipoApuesta.class, id);

        }catch (RollbackException ex){
            em.close();
            return null;
        }
        em.close();
        return tipoApuesta;

    }

    @Override
    public boolean update(TipoApuesta object) {

        EntityManager em = emf.createEntityManager();

        try{

            em.getTransaction().begin();
            TipoApuesta tipoApuestaUpdate = em.merge(object);
            em.getTransaction().commit();

        }catch (RollbackException ex){

            em.close();

        }

        em.close();
        return false;
    }

    @Override
    public void delete(Integer id) {

        EntityManager em = emf.createEntityManager();

        try{

            em.getTransaction().begin();
            TipoApuesta tipoApuesta = em.find(TipoApuesta.class, id);

            if(tipoApuesta != null){

                em.remove(tipoApuesta);
                em.getTransaction().commit();

            }

        }catch (RollbackException ex){

            em.close();

        }

        em.close();

    }

    @Override
    public List<TipoApuesta> findAll() {

        List<TipoApuesta> tipoApuestas = null;
        EntityManager em = emf.createEntityManager();

        try {

            em.getTransaction().begin();
            tipoApuestas = em.createNamedQuery("TipoApuesta.findAll", TipoApuesta.class)
                    .getResultList();

            em.getTransaction().commit();
        }catch (RollbackException ex){

            em.close();
            return null;

        }

        return tipoApuestas;

    }
}
