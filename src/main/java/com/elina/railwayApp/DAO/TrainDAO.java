package com.elina.railwayApp.DAO;

import com.elina.railwayApp.model.Train;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainDAO {

    void add(Train train);

    void update(Train train);

    void delete(Train train);

    List<Train> getAll();

    Train getById(Long id);
}
