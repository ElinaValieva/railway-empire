package com.elina.railwayApp.DAO.Implementation;

import com.elina.railwayApp.DAO.TicketDAO;
import com.elina.railwayApp.model.Schedule;
import com.elina.railwayApp.model.Seat;
import com.elina.railwayApp.model.Ticket;
import com.elina.railwayApp.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
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

    @Override
    public List<Ticket> findSameUserOnTrain(User user, Schedule schedule) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Ticket " +
                        "where schedule = :schedule and user = :user")
                .setParameter("schedule", schedule)
                .setParameter("user", user)
                .getResultList();
    }

    @Override
    public Ticket findTicketByScheduleAndSeat(Schedule schedule, Seat seat) {
        return (Ticket) sessionFactory.getCurrentSession()
                .createQuery("from Ticket " +
                        "where schedule = :schedule and seat = :seat")
                .setParameter("schedule", schedule)
                .setParameter("seat", seat)
                .uniqueResult();
    }

    @Override
    public List<Ticket> getByUser(User user) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Ticket " +
                        "where user = :user")
                .setParameter("user", user)
                .getResultList();
    }

    @Override
    public List<Ticket> getByDate(Date date) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Ticket " +
                        "where date(schedule.dateDeparture) = :date")
                .setParameter("date", date)
                .getResultList();
    }
}
