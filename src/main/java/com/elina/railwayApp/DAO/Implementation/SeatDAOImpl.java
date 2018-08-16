package com.elina.railwayApp.DAO.Implementation;

import com.elina.railwayApp.DAO.SeatDAO;
import com.elina.railwayApp.model.Seat;
import com.elina.railwayApp.model.Train;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SeatDAOImpl<E extends Seat> extends GenericDAOImpl<E> implements SeatDAO<E> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Seat getByTrainAndCarriageAndSeat(Train train, Integer carriage, Integer seat) {
        return (Seat) sessionFactory.getCurrentSession()
                .createQuery("from Seat where " +
                        "train = :train and seat = :seat and carriage = :carriage")
                .setParameter("train", train)
                .setParameter("seat", seat)
                .setParameter("carriage", carriage)
                .uniqueResult();
    }
}
