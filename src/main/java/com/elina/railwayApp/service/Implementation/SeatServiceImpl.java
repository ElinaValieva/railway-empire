package com.elina.railwayApp.service.Implementation;

import com.elina.railwayApp.DAO.SeatDAO;
import com.elina.railwayApp.model.Seat;
import com.elina.railwayApp.model.Train;
import com.elina.railwayApp.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {

    @Autowired
    private SeatDAO seatDAO;

    @Override
    @Transactional
    public void add(Seat seat) {
        seatDAO.add(seat);
    }

    @Override
    @Transactional
    public void delete(Seat seat) {
        seatDAO.delete(seat);
    }

    @Override
    @Transactional
    public void update(Seat seat) {
        seatDAO.update(seat);
    }

    @Override
    @Transactional
    public List<Seat> getAll() {
        return seatDAO.getAll();
    }

    @Override
    @Transactional
    public Seat getById(Long id) {
        return (Seat) seatDAO.getById(id);
    }

    @Override
    @Transactional
    public Seat getByTrainAndCarriageAndSeat(Train train, Integer carriage, Integer seat) {
        return seatDAO.getByTrainAndCarriageAndSeat(train, carriage, seat);
    }
}
