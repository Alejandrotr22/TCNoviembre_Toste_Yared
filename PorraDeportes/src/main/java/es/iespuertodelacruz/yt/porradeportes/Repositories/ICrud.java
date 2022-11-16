package es.iespuertodelacruz.yt.porradeportes.Repositories;

import es.iespuertodelacruz.yt.porradeportes.entities.Usuario;

import java.util.ArrayList;
import java.util.List;

public interface ICrud<T, E> {

    T save(T object);

    T findByID(E id);

    void update(T object);

    void delete(E id);

    List<T> findAll();

}
