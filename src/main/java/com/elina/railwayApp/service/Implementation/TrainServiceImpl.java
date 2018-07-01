package com.elina.railwayApp.service.Implementation;

import com.elina.railwayApp.DAO.StatusDAO;
import com.elina.railwayApp.DAO.TrainDAO;
import com.elina.railwayApp.model.Seat;
import com.elina.railwayApp.model.Status;
import com.elina.railwayApp.model.Train;
import com.elina.railwayApp.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TrainServiceImpl implements TrainService {

    @Autowired
    private TrainDAO trainDAO;

    @Autowired
    private StatusDAO statusDAO;

    @Override
    @Transactional
    public void add(Train train) {
        trainDAO.add(train);
    }

    @Override
    @Transactional
    public void add(Train train, int cntCarriage, int cntSeats) {

        Set<Seat> seats = new HashSet<>();
        for (int i = 1; i <= cntCarriage; i++)
            for (int j = 1; j <= cntSeats; j++) {
                Seat seat = new Seat();
                seat.setCarriage(i);
                seat.setSeat(j);
                seat.setTrain(train);
                seats.add(seat);
            }
        train.setSeats(seats);
        Status status = statusDAO.getByName("NOT_USED");
        train.setStatus(status);
        trainDAO.add(train);
    }

    @Override
    @Transactional
    public void delete(Train train) {
        Status status = statusDAO.getByName("DELETED");
        train.setStatus(status);
        trainDAO.update(train);
    }

    @Override
    @Transactional
    public void update(Train train) {
        Status status = statusDAO.getByName("NOT_USED");
        train.setStatus(status);
        trainDAO.update(train);
    }

    @Override
    @Transactional
    public List<Train> getAll() {
        return trainDAO.getAll();
    }

    @Override
    @Transactional
    public Train getById(Long id) {
        return trainDAO.getById(id);
    }

    @Override
    @Transactional
    public Train getByName(String name) {
        return trainDAO.getByName(name);
    }
}
