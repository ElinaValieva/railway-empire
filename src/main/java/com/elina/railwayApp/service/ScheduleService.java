package com.elina.railwayApp.service;

import com.elina.railwayApp.model.Schedule;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface ScheduleService {

    void add(Schedule schedule);

    void delete(Schedule schedule);

    void update(Schedule schedule);

    List<Schedule> getAll();

    Schedule getById(Long id);

    List<Schedule> getByDate(Date dateArrival);

    List<Schedule> getByStationsAndDate(Schedule schedule);

    List<Schedule> getByDateAndTrainToCheckIntersection(Schedule schedule);

    List<Schedule> getByTrainAndDate(Schedule schedule);
}

