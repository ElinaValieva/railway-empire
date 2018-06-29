package com.elina.railwayApp.DAO.Implementation;

import com.elina.railwayApp.DAO.ScheduleDAO;
import com.elina.railwayApp.model.Schedule;
import com.elina.railwayApp.model.Station;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.time.LocalDate;
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
    public List<Schedule> getByDate(String date1) throws ParseException {
        LocalDate localDate = LocalDate.of(2018,6,29);
        return sessionFactory.getCurrentSession()
                .createQuery("from Schedule " +
                        "where date(dateArrival) = :date")
                .setParameter("date", localDate)
                .getResultList();
    }

    /**
     * @param date
     * @param stationArrival
     * @param stationDepartment
     * @return all schedule by stations and date
     */
    @Override
    public List<Schedule> getByStationAndDate(String date, Station stationArrival, Station stationDepartment) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Schedule where " +
                        "stationArrival = :stationArrival and " +
                        "stationDepartment = :stationDepartment and " +
                        "dateArrival = :date")
                .setParameter("stationArrival", stationArrival)
                .setParameter("stationDepartment", stationDepartment)
                .setParameter("date", date)
                .getResultList();
    }

    /**
     * @param schedule
     * @return intersection schedules with current schedule
     */
    @Override
    public List<Schedule> getByDateAndTrain(Schedule schedule) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Schedule where train = :train " +
                        "and (:dateA between dateArrival  and dateDepartment) or (:dateD between dateArrival and dateDepartment)")
                .setParameter("train", schedule.getTrain())
                .setParameter("dateA", schedule.getDateArrival())
                .setParameter("dateD", schedule.getDateDepartment())
                .getResultList();
    }
}
