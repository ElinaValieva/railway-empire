package com.elina.railwayApp.DAO;

import com.elina.railwayApp.model.Seat;
import com.elina.railwayApp.model.Train;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatDAO<E extends Seat> extends GenericDAO<E> {

    Seat getByTrainAndCarriageAndSeat(Train train, Integer carriage, Integer seat);
}
