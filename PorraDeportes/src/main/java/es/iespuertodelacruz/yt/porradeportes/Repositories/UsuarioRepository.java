package es.iespuertodelacruz.yt.porradeportes.Repositories;

import es.iespuertodelacruz.yt.porradeportes.entities.Usuario;
import es.iespuertodelacruz.yt.porradeportes.entities.Apuesta;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;
import java.util.List;

public class UsuarioRepository implements ICrud<Usuario, Integer>{

    EntityManagerFactory emf;

    public UsuarioRepository(EntityManagerFactory emf){
        this.emf = emf;
    }

    @Override
    public Usuario save(Usuario object) {

        Usuario usuario = null;

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            for (Apuesta apuesta : object.getApuestas()) {
                apuesta.setUsuario(object);
            }
            em.persist(object);
            em.getTransaction().commit();
            usuario = new Usuario(object);

        }catch (RollbackException ex){
            em.close();
            return null;
        }
        em.close();
        return usuario;



    }

    @Override
    public Usuario findByID(Integer id) {

        EntityManager em = emf.createEntityManager();
        Usuario usuario;
        try {

            usuario = em.find(Usuario.class, id);

        }catch (RollbackException ex){
            em.close();
            return null;
        }
        em.close();
        return usuario;

    }

    @Override
    public Boolean update(Usuario object) {

        EntityManager em = emf.createEntityManager();

        try{

        em.getTransaction().begin();
        Usuario usuarioUpdate = em.merge(object);
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
        Usuario usuario = em.find(Usuario.class, id);
        if(usuario != null){
            em.remove(usuario);
            em.getTransaction().commit();
        }else{
            em.close();
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
    public List<Usuario> findAll() {

        List<Usuario> usuarios = null;
        EntityManager em = emf.createEntityManager();

        try {

            em.getTransaction().begin();
            usuarios = em.createNamedQuery("Usuario.findAll", Usuario.class)
                    .getResultList();
            em.getTransaction().commit();

        }catch (RollbackException ex){
            em.close();
            return null;
        }
        em.close();

        return usuarios;


    }

    public Usuario findByEmail(String email) {

        EntityManager em = emf.createEntityManager();
        Usuario usuario;
        try {

            usuario = em.createNamedQuery("Usuario.findByEmail", Usuario.class)
                    .setParameter("email", email)
                    .getSingleResult();


        }catch (Exception ex){
            em.close();
            return null;
        }
        em.close();
        return usuario;

    }

    public Usuario findByUser(String user) {

        EntityManager em = emf.createEntityManager();
        Usuario usuario;
        try {

            usuario = em.createNamedQuery("Usuario.findByUser", Usuario.class)
                    .setParameter("usuario", user)
                    .getSingleResult();


        }catch (Exception ex){
            em.close();
            return null;
        }
        em.close();
        return usuario;

    }
}
