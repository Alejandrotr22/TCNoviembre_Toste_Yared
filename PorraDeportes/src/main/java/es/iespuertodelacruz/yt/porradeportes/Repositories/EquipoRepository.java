package es.iespuertodelacruz.yt.porradeportes.Repositories;

import es.iespuertodelacruz.yt.porradeportes.entities.Apuesta;
import es.iespuertodelacruz.yt.porradeportes.entities.Equipo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;
import java.util.List;

public class EquipoRepository implements ICrud<Equipo, Integer> {

    EntityManagerFactory emf;

    public EquipoRepository(EntityManagerFactory emf){this.emf = emf; }

    @Override
    public Equipo save(Equipo object) {

        Equipo equipo = null;

        EntityManager em = emf.createEntityManager();

        try {

            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
            equipo = new Equipo(object);

        }catch (RollbackException ex){

            em.close();
            return null;

        }

        em.close();
        return equipo;


    }

    @Override
    public Equipo findByID(Integer id) {

        EntityManager em = emf.createEntityManager();
        Equipo equipo;

        try {

            equipo = em.find(Equipo.class, id);

        }catch (RollbackException ex){

            em.close();
            return null;

        }

        em.close();
        return equipo;

    }

    @Override
    public Boolean update(Equipo object) {

        EntityManager em = emf.createEntityManager();

        try {

            em.getTransaction().begin();
            em.merge(object);
            em.getTransaction().commit();

        }catch (RollbackException ex){

            em.close();
            return null;

        }

        em.close();
        return true;

    }

    @Override
    public Boolean delete(Integer id) {

        EntityManager em = emf.createEntityManager();

        try {

            em.getTransaction().begin();
            Equipo equipo = em.find(Equipo.class, id);
            if(equipo != null){

                em.remove(equipo);
                em.getTransaction().commit();

            }else{
                return false;
            }

        }catch (RollbackException ex){

            em.close();
            return null;

        }

        em.close();
        return true;

    }

    @Override
    public List<Equipo> findAll() {


        List<Equipo> equipos = null;
        EntityManager em = emf.createEntityManager();

        try {

            em.getTransaction().begin();
            equipos = em.createNamedQuery("Equipo.findAll", Equipo.class)
                    .getResultList();
            em.getTransaction().commit();

        }catch (RollbackException ex){
            em.close();
            return null;
        }
        em.close();

        return equipos;

    }
}
