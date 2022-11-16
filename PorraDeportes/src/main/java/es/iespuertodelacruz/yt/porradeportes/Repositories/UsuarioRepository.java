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
                apuesta.setIdUsuario(object);
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
        Usuario usuario = em.find(Usuario.class, id);
        em.close();

        return usuario;

    }

    @Override
    public void update(Usuario object) {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Usuario usuarioDDBB = em.find(Usuario.class, object.getId());
        usuarioDDBB = new Usuario(object);
        em.getTransaction().commit();

        em.close();

    }

    @Override
    public void delete(Integer id) {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Usuario usuario = em.find(Usuario.class, id);
        if(usuario != null){
            if(usuario.getApuestas() != null){
                Apuesta[] array = usuario.getApuestas().toArray(new Apuesta[usuario.getApuestas().size()]);
                for (Apuesta apuesta: array) {
                    usuario.getApuestas().remove(apuesta);
                    em.remove(apuesta);
                }
            }

            /*
            usuario.getApuestas().forEach(a -> em.remove(a));
            usuario.getApuestas().clear();
            */
            em.remove(usuario);
            em.getTransaction().commit();

        }
    }

    @Override
    public List<Usuario> findAll() {

        List<Usuario> usuarios = null;
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.createNamedQuery("Usuario.findAll", Usuario.class)
                .getResultList();
        em.getTransaction().commit();
        em.close();

        return usuarios;


    }
}
