package com.elina.railwayApp.DAO.Implementation;

import com.elina.railwayApp.DAO.TrainDAO;
import com.elina.railwayApp.model.Schedule;
import com.elina.railwayApp.model.Train;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class TrainDAOImpl<E extends Train> extends GenericDAOImpl<E> implements TrainDAO<E> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Train getByName(String name) {
        return (Train) sessionFactory.getCurrentSession()
                .createQuery("from Train where name = :name")
                .setParameter("name", name)
                .uniqueResult();
    }

    @Override
    public Schedule getLastSchedule(Train train) {
        Date date = new Date();
        List<Schedule> schedules = sessionFactory.getCurrentSession()
                .createQuery("from Schedule where train = :train and dateArrival < :date order by dateArrival desc")
                .setParameter("train", train)
                .setParameter("date", date)
                .getResultList();
        return schedules.isEmpty() ? null : schedules.get(0);
    }
}
