package com.elina.railwayApp.DAO.Implementation;

import com.elina.railwayApp.DAO.ScheduleDAO;
import com.elina.railwayApp.model.Schedule;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
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

    /**
     * @param date
     * @return schedule by date
     */
    @Override
    public List<Schedule> getByDate(Date date) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Schedule " +
                        "where date(dateArrival) = :date")
                .setParameter("date", date)
                .getResultList();
    }

    /**
     * @param schedule
     * @return all schedule by arrival and department stations and date
     */
    @Override
    public List<Schedule> getByStationsAndDate(Schedule schedule) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Schedule where " +
                        "stationArrival = :stationArrival and " +
                        "stationDepartment = :stationDepartment and " +
                        "date(dateArrival) = :date")
                .setParameter("stationArrival", schedule.getStationArrival())
                .setParameter("stationDepartment", schedule.getStationDepartment())
                .setParameter("date", schedule.getDateArrival())
                .getResultList();
    }

    /**
     * @param schedule
     * @return intersection schedules with current schedule
     */
    @Override
    public List<Schedule> getByDateAndTrainToCheckIntersection(Schedule schedule) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Schedule where train = :train " +
                        "and (:dateA between dateArrival  and dateDepartment) or " +
                        "(:dateD between dateArrival and dateDepartment)")
                .setParameter("train", schedule.getTrain())
                .setParameter("dateA", schedule.getDateArrival())
                .setParameter("dateD", schedule.getDateDepartment())
                .getResultList();
    }

    /**
     * @param schedule
     * @return direct schedules by train and date
     */
    @Override
    public List<Schedule> getByTrainAndDate(Schedule schedule) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Schedule where train = :train and " +
                        "date(dateArrival) = :date")
                .setParameter("train", schedule.getTrain())
                .setParameter("date", schedule.getDateArrival())
                .getResultList();
    }
}
