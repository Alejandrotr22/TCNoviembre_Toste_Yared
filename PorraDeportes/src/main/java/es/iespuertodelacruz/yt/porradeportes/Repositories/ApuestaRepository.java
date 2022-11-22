package es.iespuertodelacruz.yt.porradeportes.Repositories;

import es.iespuertodelacruz.yt.porradeportes.entities.Apuesta;
import es.iespuertodelacruz.yt.porradeportes.entities.Rol;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;
import java.util.List;

public class ApuestaRepository implements ICrud<Apuesta, Integer>{

    EntityManagerFactory emf;

    public ApuestaRepository(EntityManagerFactory emf){ this.emf = emf; }

    @Override
    public Apuesta save(Apuesta object) {

        Apuesta apuesta = null;

        EntityManager em = emf.createEntityManager();

        try {

            em.getTransaction().begin();

            em.persist(object);

            em.getTransaction().commit();
            apuesta = new Apuesta(object);

        }catch (RollbackException ex){

            em.close();
            return null;

        }
        em.close();
        return apuesta;

    }

    @Override
    public Apuesta findByID(Integer id) {

        EntityManager em = emf.createEntityManager();
        Apuesta apuesta;

        try{

            apuesta = em.find(Apuesta.class, id);

        }catch (RollbackException ex){

            em.close();
            return null;

        }

        em.close();
        return apuesta;

    }

    @Override
    public boolean update(Apuesta object) {

        EntityManager em = emf.createEntityManager();

        try {

            em.getTransaction().begin();
            Apuesta apuestaUpdate = em.merge(object);
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

        try {

            em.getTransaction().begin();
            Apuesta apuesta = em.find(Apuesta.class, id);
            if(apuesta != null){

                em.remove(apuesta);
                em.getTransaction().commit();

            }

        }catch (RollbackException ex){

            em.close();

        }

        em.close();

    }

    @Override
    public List<Apuesta> findAll() {

        List<Apuesta> apuestas = null;
        EntityManager em = emf.createEntityManager();

        try {

            em.getTransaction().begin();
            apuestas = em.createNamedQuery("Apuesta.findAll", Apuesta.class)
                    .getResultList();
            em.getTransaction().commit();

        }catch (RollbackException ex){
            em.close();
            return null;
        }
        em.close();

        return apuestas;

    }
}
