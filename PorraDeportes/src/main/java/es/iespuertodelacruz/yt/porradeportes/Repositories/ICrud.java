package es.iespuertodelacruz.yt.porradeportes.Repositories;

import es.iespuertodelacruz.yt.porradeportes.entities.Usuario;

import java.util.ArrayList;
import java.util.List;

public interface ICrud<T, E> {

    T save(T object);

    T findByID(E id);

    Boolean update(T object);

    Boolean delete(E id);

    List<T> findAll();

}
