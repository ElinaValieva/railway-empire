package com.elina.railwayApp.service.Implementation;

import com.elina.railwayApp.DAO.ScheduleDAO;
import com.elina.railwayApp.model.Schedule;
import com.elina.railwayApp.model.Station;
import com.elina.railwayApp.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleDAO scheduleDAO;

    @Override
    @Transactional
    public void add(Schedule schedule) {
        scheduleDAO.add(schedule);
    }

    @Override
    @Transactional
    public void delete(Schedule schedule) {
        scheduleDAO.delete(schedule);
    }

    @Override
    @Transactional
    public void update(Schedule schedule) {
        scheduleDAO.update(schedule);
    }

    @Override
    @Transactional
    public List<Schedule> getAll() {
        return scheduleDAO.getAll();
    }

    @Override
    @Transactional
    public Schedule getById(Long id) {
        return scheduleDAO.getById(id);
    }

    @Override
    @Transactional
    public List<Schedule> getByDateArrival(String dateArrival) {
        return scheduleDAO.getByDate(dateArrival);
    }

    @Override
    @Transactional
    public List<Schedule> getByStationAndDate(String date, Station stationArrival, Station stationDepartment) {
        return scheduleDAO.getByStationAndDate(date, stationArrival, stationDepartment);
    }
}
