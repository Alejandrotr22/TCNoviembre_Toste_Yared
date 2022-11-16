package es.iespuertodelacruz.yt.porradeportes.Repositories;

<<<<<<< HEAD
import es.iespuertodelacruz.yt.porradeportes.entities.Usuario;

=======
import es.iespuertodelacruz.yt.porradeportes.entities.Apuesta;
import es.iespuertodelacruz.yt.porradeportes.entities.Usuario;

import javax.persistence.EntityManager;
>>>>>>> d7591cc (Terminada la creacion de las entities con JPABuddy y creado el repository de usuario y hecho un test sobre este y rol para comprobar que las entities se habian creado bien #6)
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository implements ICrud<Usuario, Integer>{

    EntityManagerFactory emf;

    public UsuarioRepository(EntityManagerFactory emf){
        this.emf = emf;
    }

    @Override
    public Usuario save(Usuario object) {
<<<<<<< HEAD
    return null;
=======

        Usuario usuario = null;

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        for (Apuesta apuesta: object.getApuestas()) {
            apuesta.setIdUsuario(object);
        }
        em.persist(object);
        em.getTransaction().commit();
        usuario = new Usuario(object);

        em.close();

        return usuario;


>>>>>>> d7591cc (Terminada la creacion de las entities con JPABuddy y creado el repository de usuario y hecho un test sobre este y rol para comprobar que las entities se habian creado bien #6)
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
