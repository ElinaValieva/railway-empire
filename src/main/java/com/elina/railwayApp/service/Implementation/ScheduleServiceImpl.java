package com.elina.railwayApp.service.Implementation;

import com.elina.railwayApp.DAO.ScheduleDAO;
import com.elina.railwayApp.DAO.StatusDAO;
import com.elina.railwayApp.DTO.ScheduleDTO;
import com.elina.railwayApp.DTO.TransferScheduleDTO;
import com.elina.railwayApp.configuration.common.Utils;
import com.elina.railwayApp.model.Schedule;
import com.elina.railwayApp.model.Station;
import com.elina.railwayApp.model.Status;
import com.elina.railwayApp.model.Train;
import com.elina.railwayApp.service.ScheduleService;
import com.elina.railwayApp.service.StationService;
import com.elina.railwayApp.service.TrainService;
import lombok.extern.log4j.Log4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@Log4j
@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleDAO scheduleDAO;

    @Autowired
    private StatusDAO statusDAO;

    @Autowired
    private StationService stationService;

    @Autowired
    private TrainService trainService;

    @Autowired
    private ModelMapper modelMapper;

    private static final int MIN_DELTA_TRANSFER = 15;
    private static final int MAX_DELTA_TRANSFER = 360;

    /**
     * create schedule (check intersection of times and correctness of times)
     *
     * @param scheduleDTO
     */
    @Override
    @Transactional
    public void add(ScheduleDTO scheduleDTO) throws ParseException {
        Date dateDeparture = Utils.parseToDateTime(scheduleDTO.getDateDeparture());
        Date dateArrival = Utils.parseToDateTime(scheduleDTO.getDateArrival());
        Train train = trainService.getByName(scheduleDTO.getTrainName());
        Station stationArrival = stationService.getByName(scheduleDTO.getStationArrivalName());
        Station stationDeparture = stationService.getByName(scheduleDTO.getStationDepartureName());
        Schedule schedule = new Schedule();
        schedule.setStationDeparture(stationDeparture);
        schedule.setStationArrival(stationArrival);
        schedule.setDateDeparture(dateDeparture);
        schedule.setDateArrival(dateArrival);
        schedule.setTrain(train);
        if (stationArrival != null && stationDeparture != null && train != null) {
            if (!stationArrival.equals(stationDeparture)
                    && dateDeparture.before(dateArrival)
                    && getByDateAndTrainToCheckIntersection(schedule).isEmpty()) {
                Status status = statusDAO.getByName("WORKED");
                schedule.getStationDeparture().setStatus(status);
                schedule.getStationArrival().setStatus(status);
                scheduleDAO.add(schedule);
                log.info("SCHEDULE WAS CREATED!");
            } else log.warn("WRONG DATETIME or SAME STATIONS FOR SCHEDULE");
        } else log.warn("CAN'T ADD SCHEDULE WITH EMPTY VALUES");
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
     * @param dateTransferDeparture
     * @param dateTransferArrival
     * @return
     */

    @Transactional
    public Map<Station, List<Schedule>> getTransferList(String dateTransferDeparture, String dateTransferArrival) throws ParseException {
        Date dateDeparture = Utils.parseToDate(dateTransferDeparture);
        Date dateArrival = Utils.parseToDate(dateTransferArrival);
        List<Schedule> schedules = getByDates(dateDeparture, dateArrival);
        Map<Station, List<Schedule>> mapStationsForTransfer = new HashMap<>();
        for (Schedule schedule :
                schedules) {
            if (mapStationsForTransfer.get(schedule.getStationDeparture()) == null) {
                List<Schedule> listSchedulesForCurrentSchedule = getByStationArrivalAndDate(schedule);
                if (!listSchedulesForCurrentSchedule.isEmpty())
                    mapStationsForTransfer.put(schedule.getStationDeparture(), listSchedulesForCurrentSchedule);
            }
        }
        return mapStationsForTransfer;
    }

    /**
     * trip with 1 transfer
     * can add transfer - trip in schedule if 15 min < transfer < 6 hour
     *
     * @param scheduleDTO
     * @return
     */

    @Override
    @Transactional
    public List<TransferScheduleDTO> getTransferSchedules(ScheduleDTO scheduleDTO) throws ParseException {
        Map<Station, List<Schedule>> mapStationForTransfer = getTransferList(scheduleDTO.getDateDeparture(), scheduleDTO.getDateArrival());
        Station stationDeparture = stationService.getByName(scheduleDTO.getStationDepartureName());
        Station stationArrival = stationService.getByName(scheduleDTO.getStationArrivalName());
        List<Schedule> schedules = mapStationForTransfer.get(stationDeparture);
        List<TransferScheduleDTO> transferScheduleDTOS = new ArrayList<>();
        for (Schedule schedule :
                schedules) {
            List<Schedule> transferSchedule = new ArrayList<>();
            if (!schedule.getStationArrival().equals(stationArrival)) {
                transferSchedule = mapStationForTransfer.get(schedule.getStationArrival())
                        .stream()
                        .filter(x -> x.getStationArrival().equals(stationArrival)
                                && schedule.getDateArrival().before(x.getDateDeparture()) &&
                                Utils.checkTransfer(schedule.getDateArrival(), x.getDateDeparture(), MIN_DELTA_TRANSFER, MAX_DELTA_TRANSFER))
                        .collect(Collectors.toList());

                if (!transferSchedule.isEmpty())
                    transferSchedule.stream().forEach(transfer -> {

                        TransferScheduleDTO transferScheduleDTO = new TransferScheduleDTO();
                        transferScheduleDTO.setStationDepartureName(schedule.getStationDeparture().getName());
                        transferScheduleDTO.setStationArrivalName(transfer.getStationArrival().getName());
                        transferScheduleDTO.setStationIntermediateName(schedule.getStationArrival().getName());
                        transferScheduleDTO.setDateDeparture(schedule.getDateDeparture().toString());
                        transferScheduleDTO.setDateIntermediateDeparture(schedule.getDateArrival().toString());
                        transferScheduleDTO.setDateIntermediateArrival(transfer.getDateDeparture().toString());
                        transferScheduleDTO.setDateArrival(transfer.getDateArrival().toString());
                        transferScheduleDTO.setTrainDepartureName(schedule.getTrain().getName());
                        transferScheduleDTO.setTrainArrivalName(transfer.getTrain().getName());
                        transferScheduleDTOS.add(transferScheduleDTO);
                    });
            }
        }
        return transferScheduleDTOS;
    }

    @Override
    @Transactional
    public boolean checkWorkingStation(Station station, Date date) {
        return scheduleDAO.getWorkingStation(station, date).isEmpty();
    }

    @Override
    @Transactional
    public List<ScheduleDTO> getDirectSchedulesFromDTOByStationsAndDatesAndTrain(ScheduleDTO scheduleDTO) throws
            ParseException {
        List<Schedule> schedules = new ArrayList<>();
        Schedule schedule = new Schedule();
        Train train = trainService.getByName(scheduleDTO.getTrainName());
        Station stationDeparture = stationService.getByName(scheduleDTO.getStationDepartureName());
        Station stationArrival = stationService.getByName(scheduleDTO.getStationArrivalName());
        Date dateDeparture = Utils.parseToDate(scheduleDTO.getDateDeparture());
        Date dateArrival = Utils.parseToDate(scheduleDTO.getDateArrival());
        if (train != null && stationArrival != null && stationDeparture != null) {
            schedule.setTrain(train);
            schedule.setDateArrival(dateArrival);
            schedule.setDateDeparture(dateDeparture);
            schedule.setStationDeparture(stationDeparture);
            schedule.setStationArrival(stationArrival);
            schedules = scheduleDAO.getByStationsAndDatesAndTrains(schedule);
        }
        return schedules.stream()
                .map(x -> modelMapper.map(x, ScheduleDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<ScheduleDTO> getDirectSchedulesFromDTOByStations(ScheduleDTO scheduleDTO) throws ParseException {
        Station stationDepartureForDirectSchedule = stationService.getByName(scheduleDTO.getStationDepartureName());
        Station stationArrivalForDirectSchedule = stationService.getByName(scheduleDTO.getStationArrivalName());
        List<Schedule> schedules = new ArrayList<>();
        if (stationArrivalForDirectSchedule != null && stationDepartureForDirectSchedule != null) {
            Schedule schedule = new Schedule();
            schedule.setStationDeparture(stationDepartureForDirectSchedule);
            schedule.setStationArrival(stationArrivalForDirectSchedule);
            Date dateDeparture = Utils.parseToDate(scheduleDTO.getDateDeparture());
            schedule.setDateDeparture(dateDeparture);
            if (!scheduleDTO.getDateArrival().isEmpty()) {
                Date dateArrival = Utils.parseToDate(scheduleDTO.getDateArrival());
                schedule.setDateArrival(dateArrival);
                schedules = scheduleDAO.getByStationsAndDates(schedule);
            } else schedules = scheduleDAO.getByStationsAndDate(schedule);
            log.info("FOUND SCHEDULES BY STATIONS AND DATE");
        } else log.warn("CAN'T FOUND SCHEDULES BY STATIONS AND DATE. WRONG PARAMETERS");

        return schedules.stream()
                .map(x -> modelMapper.map(x, ScheduleDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<ScheduleDTO> getDirectSchedulesFromDTOByTrain(ScheduleDTO scheduleDTO) throws ParseException {
        Train train = trainService.getByName(scheduleDTO.getTrainName());
        List<Schedule> schedules = new ArrayList<>();
        if (train != null) {
            Schedule schedule = new Schedule();
            Date dateDeparture = Utils.parseToDate(scheduleDTO.getDateDeparture());
            schedule.setDateDeparture(dateDeparture);
            schedule.setTrain(train);
            if (!scheduleDTO.getDateArrival().isEmpty()) {
                Date dateArrival = Utils.parseToDate(scheduleDTO.getDateArrival());
                schedule.setDateArrival(dateArrival);
                schedules = scheduleDAO.getByTrainAndDates(schedule);
            } else schedules = scheduleDAO.getByTrainAndDate(schedule);
            log.info("FOUND SCHEDULES BY TRAIN AND DATE");
        } else log.warn("CAN'T FOUND SCHEDULES BY TRAIN AND DATE. WRONG PARAMETERS");

        return schedules.stream()
                .map(x -> modelMapper.map(x, ScheduleDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<ScheduleDTO> getDirectSchedulesFromDTOByDates(ScheduleDTO scheduleDTO) throws ParseException {
        Date dateDeparture = Utils.parseToDate(scheduleDTO.getDateDeparture());
        List<Schedule> schedules;
        if (!scheduleDTO.getDateArrival().isEmpty()) {
            Date dateArrival = Utils.parseToDate(scheduleDTO.getDateArrival());
            schedules = scheduleDAO.getByDates(dateDeparture, dateArrival);
        } else schedules = scheduleDAO.getByDate(dateDeparture);

        return schedules.stream()
                .map(x -> modelMapper.map(x, ScheduleDTO.class))
                .collect(Collectors.toList());
    }

}
