package es.iespuertodelacruz.yt.porradeportes.Repositories;

import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;

public class UsuarioRepository implements ICrud<Usuario, Integer>{

    EntityManagerFactory emf;

    public UsuarioRepository(EntityManagerFactory emf){
        this.emf = emf;
    }

    @Override
    public Usuario save(Usuario object) {



    }

    @Override
    public Usuario findByID(Integer id) {
        return null;
    }

    @Override
    public void update(Usuario object) {

    }

    @Override
    public void delete(Usuario object) {

    }

    @Override
    public ArrayList<Usuario> findAll() {
        return null;
    }
}
