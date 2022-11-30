package es.iespuertodelacruz.yt.porradeportes.Repositories;

import es.iespuertodelacruz.yt.porradeportes.entities.Apuesta;
import es.iespuertodelacruz.yt.porradeportes.entities.Equipo;
import es.iespuertodelacruz.yt.porradeportes.entities.Rol;
import es.iespuertodelacruz.yt.porradeportes.entities.Usuario;

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
    public Boolean update(Apuesta object) {

        EntityManager em = emf.createEntityManager();

        try {

            em.getTransaction().begin();
            Apuesta apuestaUpdate = em.merge(object);
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
            Apuesta apuesta = em.find(Apuesta.class, id);
            if(apuesta != null){

                em.remove(apuesta);
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

    public List<Apuesta> findAllById(Usuario usuario) {

        List<Apuesta> apuestas = null;
        EntityManager em = emf.createEntityManager();

        try {

            em.getTransaction().begin();
            apuestas = em.createNamedQuery("Apuesta.findAllById",Apuesta.class)
                    .setParameter("usuario",usuario)
                    .getResultList();
            em.getTransaction().commit();

        }catch (RollbackException ex){
            em.close();
            return null;
        }
        em.close();

        return apuestas;

    }
    public List<Apuesta> findAllbyPrediccion(Integer id) {

        List apuestas = null;
        EntityManager em = emf.createEntityManager();

        try {

            em.getTransaction().begin();
            String query = "SELECT a.* FROM apuestas as a INNER JOIN eventos as e on a.id_evento=e.id WHERE a.prediccion = e.resultado AND a.estado != 'Rechazada' and a.estado = 'Realizada' and a.id_evento = "+id ;
            apuestas = em.createNativeQuery(query,Apuesta.class).getResultList();
            em.getTransaction().commit();

        }catch (RollbackException ex){
            em.close();
            return null;
        }
        em.close();

        return apuestas;

    }

    public List<Apuesta> findAllbyEquipoGanador(Integer id) {

        List apuestas = null;
        EntityManager em = emf.createEntityManager();

        try {

            em.getTransaction().begin();
            String query = "SELECT a.* FROM apuestas as a INNER JOIN eventos as e INNER JOIN equipos as eq ON a.id_evento= e.id AND e.id_equipo_ganador = eq.id WHERE a.prediccion=eq.nombre and a.estado != 'Rechazada' and a.estado = 'Realizada' and a.id_evento = "+id;
            apuestas = em.createNativeQuery(query,Apuesta.class).getResultList();
            em.getTransaction().commit();

        }catch (RollbackException ex){
            em.close();
            return null;
        }
        em.close();

        return apuestas;

    }
}
