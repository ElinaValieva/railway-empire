package com.elina.railwayApp.service;

import com.elina.railwayApp.model.Seat;
import com.elina.railwayApp.model.Train;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SeatService {

    void add(Seat seat);

    void delete(Seat seat);

    void update(Seat seat);

    List<Seat> getAll();

    Seat getById(Long id);

    Seat getByTrainAndCarriageAndSeat(Train train, Integer carriage, Integer seat);
}
