package com.commerce.service;

import com.commerce.dao.GenericDAO;

import java.io.Serializable;
import java.util.List;

public class CrudService<T, ID extends Serializable> {

    protected final GenericDAO<T, ID> dao;

    public CrudService(Class<T> entityClass) {
        this.dao = new GenericDAO<>(entityClass);
    }

    public T getById(ID id) {
        return dao.find(id);
    }

    public List<T> getAll() {
        return dao.findAll();
    }

    public void create(T entity) {
        if (entity == null) {
            throw new IllegalArgumentException("L'entite ne doit pas etre nulle");
        }
        dao.create(entity);
    }

    public T update(T entity) {
        if (entity == null) {
            throw new IllegalArgumentException("L'entite ne doit pas etre nulle");
        }
        return dao.update(entity);
    }

    public void delete(T entity) {
        if (entity == null) {
            throw new IllegalArgumentException("L'entite ne doit pas etre nulle");
        }
        dao.delete(entity);
    }
}
