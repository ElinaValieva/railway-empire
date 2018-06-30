package com.elina.railwayApp.service.Implementation;

import com.elina.railwayApp.DAO.ScheduleDAO;
import com.elina.railwayApp.configuration.common.Utils;
import com.elina.railwayApp.model.Schedule;
import com.elina.railwayApp.model.Station;
import com.elina.railwayApp.service.ScheduleService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Log4j
@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleDAO scheduleDAO;

    private static final int MIN_DELTA_TRANSFER = 15;
    private static final int MAX_DELTA_TRANSFER = 360;

    /**
     * create schedule (check intersection of times and correctness of times)
     *
     * @param schedule
     */
    @Override
    @Transactional
    public void add(Schedule schedule) {
        if (schedule.getStationArrival().getId() != schedule.getStationDeparture().getId()) {
            if (schedule.getDateDeparture().before(schedule.getDateArrival())
                    && getByDateAndTrainToCheckIntersection(schedule).isEmpty()) {
                scheduleDAO.add(schedule);
                log.info("SCHEDULE WAS CREATED!");
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
    public List<Schedule> getByDate(Date dateDeparture) {
        return scheduleDAO.getByDate(dateDeparture);
    }

    @Override
    public List<Schedule> getByDates(Date dateDeparture, Date dateArrival) {
        return scheduleDAO.getByDates(dateDeparture, dateArrival);
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
     * station1 -> [schedule2, ...., scheduleN]
     * ....
     * stationN -> [schedule1,...,scheduleK]
     *
     * @param date
     * @return
     */

    @Transactional
    public Map<Long, List<Schedule>> getTransferList(Date date) {
        Date dateDeparture = Utils.getNextDay(date);
        List<Schedule> schedules = getByDates(date, dateDeparture);
        Map<Long, List<Schedule>> mapStationsForTransfer = new HashMap<>();
        for (Schedule schedule :
                schedules) {
            if (mapStationsForTransfer.get(schedule.getStationDeparture().getId()) == null) {
                List<Schedule> listSchedulesForCurrentSchedule = getByStationArrivalAndDate(schedule);
                if (!listSchedulesForCurrentSchedule.isEmpty())
                    mapStationsForTransfer.put(schedule.getStationDeparture().getId(), listSchedulesForCurrentSchedule);
            }
        }
        return mapStationsForTransfer;
    }

    /**
     * trip with 1 transfer
     * can add transfer - trip in schedule if 15 min < transfer < 6 hour
     *
     * @param date
     * @param stationDeparture
     * @param stationArrival
     * @return
     */

    @Override
    @Transactional
    public Set<List<Schedule>> getTransferSchedules(Date date, Station stationDeparture, Station stationArrival) {
        Map<Long, List<Schedule>> mapStationForTransfer = getTransferList(date);
        Set<List<Schedule>> set = new HashSet<>();
        List<Schedule> schedules = mapStationForTransfer.get(stationDeparture.getId());
        for (Schedule schedule :
                schedules) {
            List<Schedule> transferSchedule;
            if (schedule.getStationArrival().equals(stationArrival)) {
                transferSchedule = new ArrayList<>();
                transferSchedule.add(schedule);
                set.add(transferSchedule);
            } else {
                transferSchedule = mapStationForTransfer.get(schedule.getStationArrival().getId())
                        .stream()
                        .filter(x -> x.getStationArrival().equals(stationArrival)
                                && schedule.getDateArrival().before(x.getDateDeparture()) &&
                                Utils.checkTransfer(schedule.getDateArrival(), x.getDateDeparture(), MIN_DELTA_TRANSFER, MAX_DELTA_TRANSFER))
                        .collect(Collectors.toList());
                if (!transferSchedule.isEmpty()) {
                    transferSchedule.add(schedule);
                    set.add(transferSchedule);
                }
            }
        }
        return set;
    }

    @Override
    @Transactional
    public boolean checkWorkingStation(Station station, Date date) {
        return scheduleDAO.getWorkingStation(station, date).isEmpty();
    }


}
