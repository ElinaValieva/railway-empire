package com.elina.railwayApp.DAO;

import com.elina.railwayApp.model.Schedule;
import com.elina.railwayApp.model.Train;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainDAO<E extends Train> extends GenericDAO<E> {

    Train getByName(String name);

    Schedule getLastSchedule(Train train);
}
