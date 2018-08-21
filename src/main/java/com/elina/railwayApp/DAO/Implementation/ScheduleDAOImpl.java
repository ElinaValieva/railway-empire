package com.elina.railwayApp.DAO.Implementation;

import com.elina.railwayApp.DAO.ScheduleDAO;
import com.elina.railwayApp.configuration.common.Utils;
import com.elina.railwayApp.model.Schedule;
import com.elina.railwayApp.model.Station;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Repository
public class ScheduleDAOImpl<E extends Schedule> extends GenericDAOImpl<E> implements ScheduleDAO<E> {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * @param date
     * @return schedule by date
     */
    @Override
    public List<Schedule> getByDate(Date date) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Schedule " +
                        "where date(dateDeparture) = :date " +
                        "order by dateDeparture desc ")
                .setParameter("date", date)
                .getResultList();
    }

    @Override
    public List<Schedule> getByDates(Date dateDeparture, Date dateArrival) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Schedule " +
                        "where dateDeparture between :dateDeparture and :dateArrival and dateArrival between :dateDeparture and :dateArrival " +
                        "order by id desc ")
                .setParameter("dateDeparture", dateDeparture)
                .setParameter("dateArrival", dateArrival)
                .getResultList();
    }

    /**
     * @param schedule
     * @return all schedule by arrival and departure stations and date
     */
    @Override
    public List<Schedule> getByStationsAndDate(Schedule schedule) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Schedule where " +
                        "stationArrival = :stationArrival and " +
                        "stationDeparture = :stationDeparture and " +
                        "date(dateDeparture) = :date " +
                        "order by dateDeparture desc ")
                .setParameter("stationArrival", schedule.getStationArrival())
                .setParameter("stationDeparture", schedule.getStationDeparture())
                .setParameter("date", schedule.getDateDeparture())
                .getResultList();
    }

    /**
     * @param schedule
     * @return all schedules by stations and dates departure and arrival
     */
    @Override
    public List<Schedule> getByStationsAndDates(Schedule schedule) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Schedule where " +
                        "stationArrival = :stationArrival and " +
                        "stationDeparture = :stationDeparture and " +
                        "dateDeparture between :dateDeparture and :dateArrival " +
                        "order by dateDeparture desc ")
                .setParameter("stationArrival", schedule.getStationArrival())
                .setParameter("stationDeparture", schedule.getStationDeparture())
                .setParameter("dateDeparture", schedule.getDateDeparture())
                .setParameter("dateArrival", schedule.getDateArrival())
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
                        "and ((:dateD between dateDeparture  and dateArrival) or " +
                        "(:dateA between dateDeparture and dateArrival))" +
                        "order by dateDeparture desc ")
                .setParameter("train", schedule.getTrain())
                .setParameter("dateD", schedule.getDateDeparture())
                .setParameter("dateA", schedule.getDateArrival())
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
                        "date(dateDeparture) = :date " +
                        "order by dateDeparture desc ")
                .setParameter("train", schedule.getTrain())
                .setParameter("date", schedule.getDateDeparture())
                .getResultList();
    }

    /**
     * @param schedule
     * @return all schedules by train and dates departure and arrival
     */
    @Override
    public List<Schedule> getByTrainAndDates(Schedule schedule) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Schedule where train = :train and " +
                        "dateDeparture between :dateDeparture and :dateArrival " +
                        "order by dateDeparture desc ")
                .setParameter("train", schedule.getTrain())
                .setParameter("dateDeparture", schedule.getDateDeparture())
                .setParameter("dateArrival", schedule.getDateArrival())
                .getResultList();
    }

    /**
     * @param schedule
     * @return schedules by station departure and date
     */
    @Override
    public List<Schedule> getByStationArrivalAndDate(Schedule schedule) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Schedule  where " +
                        "stationDeparture = :stationDeparture " +
                        "and date(dateDeparture) = :date " +
                        "order by dateDeparture desc ")
                .setParameter("stationDeparture", schedule.getStationDeparture())
                .setParameter("date", schedule.getDateDeparture())
                .getResultList();
    }

    @Override
    public List<Schedule> getByStationsAndDatesAndTrains(Schedule schedule) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Schedule where " +
                        "stationDeparture = :stationDeparture and stationArrival = :stationArrival and " +
                        "dateDeparture between :dateDeparture and :dateArrival and " +
                        "dateArrival between :dateDeparture and :dateArrival order by " +
                        "dateDeparture desc ")
                .setParameter("stationDeparture", schedule.getStationDeparture())
                .setParameter("stationArrival", schedule.getStationArrival())
                .setParameter("dateDeparture", schedule.getDateDeparture())
                .setParameter("dateArrival", schedule.getDateArrival())
                .getResultList();
    }

    @Override
    public List<Schedule> getByStationArrivalAndDates(Station station, Date dateFrom, Date dateTo) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Schedule " +
                        "where stationArrival = :station and " +
                        "dateArrival between :dateFrom and :dateTo")
                .setParameter("station", station)
                .setParameter("dateFrom", dateFrom)
                .setParameter("dateTo", dateTo)
                .getResultList();
    }

    @Override
    public List<Schedule> getRealTimeSchedules() throws ParseException {
        Date date = Utils.getTodayDateTime();
        return sessionFactory.getCurrentSession()
                .createQuery("from Schedule " +
                        "where :date between dateDeparture and dateArrival " +
                        "order by dateDeparture desc ")
                .setParameter("date", date)
                .getResultList();
    }

    @Override
    public List<Schedule> getForToday() throws ParseException {
        Date date = Utils.getTodayDateTime();
        return sessionFactory.getCurrentSession()
                .createQuery("from Schedule " +
                        "where date(dateDeparture) = date(:date) " +
                        "order by dateDeparture asc ")
                .setParameter("date", date)
                .getResultList();
    }

    @Override
    public List<E> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Schedule " +
                        "order by dateDeparture desc ")
                .list();
    }
}
