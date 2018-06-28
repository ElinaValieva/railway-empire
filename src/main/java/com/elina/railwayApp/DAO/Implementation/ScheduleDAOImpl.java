package com.elina.railwayApp.DAO.Implementation;

import com.elina.railwayApp.DAO.ScheduleDAO;
import com.elina.railwayApp.model.Schedule;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ScheduleDAOImpl implements ScheduleDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Schedule schedule) {
        sessionFactory.getCurrentSession()
                .save(schedule);
    }

    @Override
    public void update(Schedule schedule) {
        sessionFactory.getCurrentSession()
                .update(schedule);
    }

    @Override
    public void delete(Schedule schedule) {
        sessionFactory.getCurrentSession()
                .delete(schedule);
    }

    @Override
    public List<Schedule> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Schedule ")
                .getResultList();
    }

    @Override
    public Schedule getById(Long id) {
        return (Schedule) sessionFactory.getCurrentSession()
                .createQuery("from Schedule where id = :id")
                .setParameter("id", id)
                .uniqueResult();
    }
}
