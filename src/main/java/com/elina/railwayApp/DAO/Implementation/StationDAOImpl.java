package com.elina.railwayApp.DAO.Implementation;

import com.elina.railwayApp.DAO.StationDAO;
import com.elina.railwayApp.model.Station;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StationDAOImpl implements StationDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Station station) {
        sessionFactory.getCurrentSession().save(station);
    }

    @Override
    public void delete(Station station) {
        sessionFactory.getCurrentSession().remove(station);
    }

    @Override
    public void update(Station station) {
        sessionFactory.getCurrentSession().update(station);
    }
}
