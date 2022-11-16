package es.iespuertodelacruz.yt.porradeportes.Repositories;

import es.iespuertodelacruz.yt.porradeportes.entities.Apuesta;
import es.iespuertodelacruz.yt.porradeportes.entities.Rol;
import es.iespuertodelacruz.yt.porradeportes.entities.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
        em.getTransaction().begin();

        for (Usuario usuario: object.getUsuarios()) {
            usuario.setIdRol(object);
        }
        em.persist(object);
        em.getTransaction().commit();


        em.close();

        return object;


    }

    @Override
    public Rol findByID(Integer id) {


        EntityManager em = emf.createEntityManager();
        Rol rol = em.find(Rol.class, id);
        em.close();

        return rol;

    }

    @Override
    public void update(Rol object) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<Rol> findAll() {
        return null;
    }


}
