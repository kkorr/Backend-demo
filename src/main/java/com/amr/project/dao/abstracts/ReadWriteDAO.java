package com.amr.project.dao.abstracts;

import java.util.List;

public interface ReadWriteDAO<E,K> {

    void persist(E e);
    void update(E e);
    void delete(E e);
    void deleteByKeyCascadeEnable(K id);
    void deleteByKeyCascadeIgnore(K id);
    boolean existsById(K id);
    E getByKey(K id);
    List<E> getAll();

}
