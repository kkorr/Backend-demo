package com.amr.project.service.abstracts;

import com.amr.project.dao.abstracts.ReadWriteDAO;

import java.util.List;

public interface ReadWriteService<E,K> {
    void persist(E e);
    void update(E e);
    void delete(E e);
    void deleteByKeyCascadeEnable(K id);
    void deleteByKeyCascadeIgnore(K id);
    boolean existsById(K id);
    E getByKey(K id);
    List<E> getAll();
}
