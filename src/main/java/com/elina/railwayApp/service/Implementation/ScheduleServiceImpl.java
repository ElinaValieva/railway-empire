package com.elina.railwayApp.service.Implementation;

import com.elina.railwayApp.DAO.ScheduleDAO;
import com.elina.railwayApp.model.Schedule;
import com.elina.railwayApp.model.Station;
import com.elina.railwayApp.service.ScheduleService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Log4j
@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleDAO scheduleDAO;

    /**
     * create schedule (check intersection of times and correctness of times)
     * @param schedule
     */
    @Override
    @Transactional
    public void add(Schedule schedule) {
        if (schedule.getStationArrival().getId() != schedule.getStationDepartment().getId()) {
            if (schedule.getDateArrival().before(schedule.getDateDepartment())
                    && getByTrainAndDate(schedule).isEmpty()) {
                scheduleDAO.add(schedule);
                log.info("SUCCESS");
            } else log.warn("WRONG DATETIME FOR SCHEDULE");
        } else log.warn("CAN'T ADD SCHEDULE FOR SAME STATIONS");
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
    public List<Schedule> getByDate(Date dateArrival) throws ParseException {
        return scheduleDAO.getByDate(dateArrival);
    }

    @Override
    @Transactional
    public List<Schedule> getByStationAndDate(String date, Station stationArrival, Station stationDepartment) {
        return scheduleDAO.getByStationAndDate(date, stationArrival, stationDepartment);
    }

    @Override
    public List<Schedule> getByTrainAndDate(Schedule schedule) {
        return scheduleDAO.getByDateAndTrain(schedule);
    }
}
