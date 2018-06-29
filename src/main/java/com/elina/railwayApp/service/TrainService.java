package com.elina.railwayApp.service;

import com.elina.railwayApp.model.Train;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TrainService {

    void add(Train train, int cntCarriage, int cntSeat);

    void add(Train train);

    void delete(Train train);

    void update(Train train);

    List<Train> getAll();

    Train getById(Long id);

    Train getByName(String name);
}
