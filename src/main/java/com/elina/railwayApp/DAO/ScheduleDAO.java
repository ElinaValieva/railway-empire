package com.elina.railwayApp.DAO;

import com.elina.railwayApp.model.Schedule;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleDAO {

    void add(Schedule schedule);

    void update(Schedule schedule);

    void delete(Schedule schedule);

    List<Schedule> getAll();

    Schedule getById(Long id);

}
