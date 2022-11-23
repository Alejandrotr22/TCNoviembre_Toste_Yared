package es.iespuertodelacruz.yt.porradeportes.Repositories;

import es.iespuertodelacruz.yt.porradeportes.entities.Rol;
import es.iespuertodelacruz.yt.porradeportes.entities.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;
import java.util.List;


public class RolRepository implements ICrud<Rol, Integer>{

    EntityManagerFactory emf;

    public RolRepository(EntityManagerFactory emf){
        this.emf = emf;
    }

    @Override
    public Rol save(Rol object) {

        Rol rol = null;

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            for (Usuario usuario : object.getUsuarios()) {
                usuario.setRol(object);
            }
            em.persist(object);
            em.getTransaction().commit();
            rol = new Rol(object);
        }catch (RollbackException ex){
            em.close();
            return null;
        }

        em.close();
        return rol;


    }

    @Override
    public Rol findByID(Integer id) {


        EntityManager em = emf.createEntityManager();
        Rol rol;
        try {
            rol = em.find(Rol.class, id);
        }catch (RollbackException ex) {
            em.close();
            return null;
        }
        em.close();
        return rol;

    }

    @Override
    public Boolean update(Rol object) {

        EntityManager em = emf.createEntityManager();

        try{

            em.getTransaction().begin();
            Rol rolUpdate = em.merge(object);
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
        try{

            em.getTransaction().begin();
            Rol rol = em.find(Rol.class, id);
            if(rol != null){
                em.remove(rol);
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
    public List<Rol> findAll() {

        List<Rol> roles = null;
        EntityManager em = emf.createEntityManager();

        try{

            em.getTransaction().begin();
            roles = em.createNamedQuery("Rol.findAll", Rol.class).getResultList();
            em.getTransaction().commit();

        }catch (RollbackException ex){
            em.close();
            return null;
        }

        em.close();

        return roles;



    }


}
