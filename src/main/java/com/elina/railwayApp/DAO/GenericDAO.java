package com.elina.railwayApp.DAO;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenericDAO<E> {

    void add(E entity);

    void update(E entity);

    void delete(E entity);

    List<E> getAll();

    E getById(Long id);
}
