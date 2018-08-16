package com.elina.railwayApp.DAO.Implementation;

import com.elina.railwayApp.DAO.StationDAO;
import com.elina.railwayApp.model.Station;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StationDAOImpl<E extends Station> extends GenericDAOImpl<E> implements StationDAO<E> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Station findByName(String name) {
        return (Station) sessionFactory.getCurrentSession()
                .createQuery("from Station where name = :name")
                .setParameter("name", name)
                .uniqueResult();
    }
}
