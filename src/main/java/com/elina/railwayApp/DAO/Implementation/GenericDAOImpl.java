package com.elina.railwayApp.DAO.Implementation;

import com.elina.railwayApp.DAO.GenericDAO;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public abstract class GenericDAOImpl<E> implements GenericDAO<E> {

    private Class<E> entityClass;

    public GenericDAOImpl() {
        this.entityClass = (Class<E>) GenericTypeResolver.resolveTypeArgument(getClass(), GenericDAO.class);
    }

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(E entity) {
        sessionFactory.getCurrentSession()
                .save(entity);
    }

    @Override
    public void update(E entity) {
        sessionFactory.getCurrentSession()
                .update(entity);
    }

    @Override
    public void delete(E entity) {
        sessionFactory.getCurrentSession()
                .delete(entity);
    }

    @Override
    public List<E> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from " + entityClass.getName())
                .getResultList();
    }

    @Override
    public E getById(Long id) {
        return sessionFactory.getCurrentSession()
                .get(entityClass, id);
    }
}
