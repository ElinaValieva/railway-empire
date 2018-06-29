package com.elina.railwayApp.DAO.Implementation;

import com.elina.railwayApp.DAO.ScheduleDAO;
import com.elina.railwayApp.model.Schedule;
import com.elina.railwayApp.model.Station;
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
                .createQuery("from Schedule " +
                        "where id = :id")
                .setParameter("id", id)
                .uniqueResult();
    }

    @Override
    public List<Schedule> getByDate(String date) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Schedule " +
                        "where dateArrival = :date")
                .setParameter("date", date)
                .getResultList();
    }

    @Override
    public List<Schedule> getByStationAndDate(String date, Station stationArrival, Station stationDepartment) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Schedule where " +
                        "stationArrival = :stationArrival and " +
                        "stationDepartment = :stationDepartment and " +
                        "cast(dateArrival as date) = :date")
                .setParameter("stationArrival", stationArrival)
                .setParameter("stationDepartment", stationDepartment)
                .setParameter("date", date)
                .getResultList();
    }
}
