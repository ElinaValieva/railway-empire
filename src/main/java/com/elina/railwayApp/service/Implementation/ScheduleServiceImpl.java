package com.elina.railwayApp.service.Implementation;

import com.elina.railwayApp.DAO.ScheduleDAO;
import com.elina.railwayApp.model.Schedule;
import com.elina.railwayApp.model.Station;
import com.elina.railwayApp.service.ScheduleService;
import lombok.extern.log4j.Log4j;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Log4j
@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleDAO scheduleDAO;

    @Override
    @Transactional
    public void add(Schedule schedule) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        DateTime dateArrival = dateTimeFormatter.parseDateTime(schedule.getDateArrival());
        DateTime dateDepartment = dateTimeFormatter.parseDateTime(schedule.getDateDepartment());
        if (schedule.getStationArrival().getId() != schedule.getStationDepartment().getId()) {
            if (dateArrival.isBefore(dateDepartment)) {
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
    public List<Schedule> getByDateArrival(String dateArrival) {
        return scheduleDAO.getByDate(dateArrival);
    }

    @Override
    @Transactional
    public List<Schedule> getByStationAndDate(String date, Station stationArrival, Station stationDepartment) {
        return scheduleDAO.getByStationAndDate(date, stationArrival, stationDepartment);
    }
}
