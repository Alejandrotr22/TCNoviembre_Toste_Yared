package es.iespuertodelacruz.yt.porradeportes.Repositories;

import java.util.ArrayList;

public interface ICrud<T, E> {

    T save(T object);

    T findByID(E id);

    void update(T object);

    void delete(T object);

    ArrayList<T> findAll();

}
