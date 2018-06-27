package com.elina.railwayApp.DAO.Implementation;

import com.elina.railwayApp.DAO.SeatDAO;
import com.elina.railwayApp.model.Seat;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SeatDAOImpl implements SeatDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Seat seat) {
        sessionFactory.getCurrentSession()
                .save(seat);
    }

    @Override
    public void delete(Seat seat) {
        sessionFactory.getCurrentSession()
                .delete(seat);
    }

    @Override
    public void update(Seat seat) {
        sessionFactory.getCurrentSession()
                .update(seat);
    }

    @Override
    public List<Seat> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Seat ")
                .getResultList();
    }

    @Override
    public Seat getById(Long id) {
        return (Seat) sessionFactory.getCurrentSession()
                .createQuery("from Seat where id = :id")
                .setParameter("id", id)
                .uniqueResult();
    }
}
