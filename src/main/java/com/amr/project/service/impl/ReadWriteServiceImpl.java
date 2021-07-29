package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.ReadWriteDAO;
import com.amr.project.service.abstracts.ReadWriteService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public abstract class ReadWriteServiceImpl<E,K> implements ReadWriteService<E,K> {

    private ReadWriteDAO<E,K> readWriteDAO;

    public ReadWriteServiceImpl(ReadWriteDAO<E,K> readWriteDAO) {
        this.readWriteDAO = readWriteDAO;
    }

    @Override
    @Transactional
    public void persist(E e) {
        readWriteDAO.persist(e);
    }

    @Override
    @Transactional
    public void update(E e) {
        readWriteDAO.update(e);
    }

    @Override
    @Transactional
    public void delete(E e) {
        readWriteDAO.delete(e);
    }

    @Override
    @Transactional
    public void deleteByKeyCascadeEnable(K id) {
        readWriteDAO.deleteByKeyCascadeEnable(id);
    }

    @Override
    @Transactional
    public void deleteByKeyCascadeIgnore(K id) {
        readWriteDAO.deleteByKeyCascadeIgnore(id);
    }

    @Override
    public boolean existsById(K id) {
        return readWriteDAO.existsById(id);
    }

    @Override
    public E getByKey(K id) {
        return readWriteDAO.getByKey(id);
    }

    @Override
    public List<E> getAll() {
        return readWriteDAO.getAll();
    }
}
