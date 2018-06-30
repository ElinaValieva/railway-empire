package com.elina.railwayApp.service.Implementation;

import com.elina.railwayApp.DAO.ScheduleDAO;
import com.elina.railwayApp.model.Schedule;
import com.elina.railwayApp.model.Station;
import com.elina.railwayApp.service.ScheduleService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j
@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleDAO scheduleDAO;

    /**
     * create schedule (check intersection of times and correctness of times)
     *
     * @param schedule
     */
    @Override
    @Transactional
    public void add(Schedule schedule) {
        if (schedule.getStationArrival().getId() != schedule.getStationDepartment().getId()) {
            if (schedule.getDateArrival().before(schedule.getDateDepartment())
                    && getByDateAndTrainToCheckIntersection(schedule).isEmpty()) {
                scheduleDAO.add(schedule);
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
    public List<Schedule> getByDate(Date dateArrival) {
        return scheduleDAO.getByDate(dateArrival);
    }

    @Override
    @Transactional
    public List<Schedule> getByStationsAndDate(Schedule schedule) {
        return scheduleDAO.getByStationsAndDate(schedule);
    }

    @Override
    @Transactional
    public List<Schedule> getByDateAndTrainToCheckIntersection(Schedule schedule) {
        return scheduleDAO.getByDateAndTrainToCheckIntersection(schedule);
    }

    @Override
    @Transactional
    public List<Schedule> getByTrainAndDate(Schedule schedule) {
        return scheduleDAO.getByTrainAndDate(schedule);
    }

    @Override
    @Transactional
    public List<Schedule> getByStationArrivalAndDate(Schedule schedule) {
        return scheduleDAO.getByStationArrivalAndDate(schedule);
    }

    /**
     * structure:
     * station1 : [station2, ...., stationN]
     * ....
     * stationN : [station1,...,stationK]
     * @param date
     * @return
     */

    @Override
    @Transactional
    public Map<Station, List<Schedule>> getTransferList(Date date) {
/**
 * надо сделать, чтобы он выбирал те станции которые укладываются в промежуток
 * сделать проверку на дельту, чтобы человек долго не ждал около  6 часов максимум
 */
        List<Schedule> schedules = getByDate(date);
        Map<Station, List<Schedule>> mapStationsForTransfer = new HashMap<>();
        for (Schedule schedule : schedules
                ) {
            if (mapStationsForTransfer.get(schedule.getStationArrival()) == null) {
                List<Schedule> listSchedulesForCurrentSchedule = getByStationArrivalAndDate(schedule);
                if (!listSchedulesForCurrentSchedule.isEmpty())
                    mapStationsForTransfer.put(schedule.getStationArrival(), listSchedulesForCurrentSchedule);

            }
        }
        return mapStationsForTransfer;
    }
}
