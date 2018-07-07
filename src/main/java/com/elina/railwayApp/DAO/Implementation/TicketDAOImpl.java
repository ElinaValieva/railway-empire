package com.elina.railwayApp.DAO.Implementation;

import com.elina.railwayApp.DAO.TicketDAO;
import com.elina.railwayApp.model.Schedule;
import com.elina.railwayApp.model.Seat;
import com.elina.railwayApp.model.Ticket;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TicketDAOImpl implements TicketDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Ticket ticket) {
        sessionFactory.getCurrentSession()
                .save(ticket);
    }

    @Override
    public void update(Ticket ticket) {
        sessionFactory.getCurrentSession()
                .update(ticket);
    }

    @Override
    public void delete(Ticket ticket) {
        sessionFactory.getCurrentSession()
                .delete(ticket);
    }

    @Override
    public List<Ticket> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Ticket")
                .getResultList();
    }

    @Override
    public Ticket getById(Long id) {
        return (Ticket) sessionFactory.getCurrentSession()
                .createQuery("from Ticket " +
                        "where id = :id")
                .setParameter("id", id)
                .uniqueResult();
    }

    @Override
    public List<Ticket> getBySchedule(Schedule schedule) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Ticket where schedule = :schedule")
                .setParameter("schedule", schedule)
                .getResultList();
    }
}
