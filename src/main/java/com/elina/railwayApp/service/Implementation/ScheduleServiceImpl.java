package com.elina.railwayApp.service.Implementation;

import com.elina.railwayApp.DAO.ScheduleDAO;
import com.elina.railwayApp.DAO.StatusDAO;
import com.elina.railwayApp.DTO.ScheduleDTO;
import com.elina.railwayApp.DTO.SeatDTO;
import com.elina.railwayApp.DTO.SeatsDTO;
import com.elina.railwayApp.DTO.TransferScheduleDTO;
import com.elina.railwayApp.configuration.common.Utils;
import com.elina.railwayApp.exception.BusinessLogicException;
import com.elina.railwayApp.exception.ErrorCode;
import com.elina.railwayApp.model.*;
import com.elina.railwayApp.service.ScheduleService;
import com.elina.railwayApp.service.StationService;
import com.elina.railwayApp.service.TicketService;
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

    @Autowired
    private TicketService ticketService;

    private static final int MIN_DELTA_TRANSFER = 15;
    private static final int MAX_DELTA_TRANSFER = 360;

    /**
     * create schedule (check intersection of times and correctness of times)
     *
     * @param scheduleDTO
     */
    @Override
    @Transactional
    public void add(ScheduleDTO scheduleDTO) throws ParseException, BusinessLogicException {
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
        if (stationArrival == null || stationDeparture == null || train == null)
            throw new BusinessLogicException(ErrorCode.NULL_ELEMENTS.getMessage());

        if (stationArrival.equals(stationDeparture))
            throw new BusinessLogicException(ErrorCode.SAME_STATIONS.getMessage());

        if (!dateDeparture.before(dateArrival))
            throw new BusinessLogicException(ErrorCode.WRONG_DATES.getMessage());

        if (!getByDateAndTrainToCheckIntersection(schedule).isEmpty())
            throw new BusinessLogicException(ErrorCode.INTERSECTION_SCHEDULES.getMessage());

        if (Utils.checkCurrentDay(dateDeparture))
            throw new BusinessLogicException(ErrorCode.SCHEDULE_FOR_CURRENT_DAY.getMessage());

        Status status = statusDAO.getByName("WORKED");
        schedule.getStationDeparture().setStatus(status);
        schedule.getStationArrival().setStatus(status);
        scheduleDAO.add(schedule);
        log.info("SCHEDULE WAS CREATED!");
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
    public List<ScheduleDTO> getAll() {
        List<Schedule> schedules = scheduleDAO.getAll();
        return schedules.stream().map(x -> modelMapper.map(x, ScheduleDTO.class)).collect(Collectors.toList());
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
        Date dateArrival;
        if (dateTransferArrival != null)
            dateArrival = Utils.parseToDate(dateTransferArrival);
        else
            dateArrival = Utils.getNextDay(dateTransferArrival);
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
                        transferScheduleDTO.setIdScheduleDeparture(schedule.getId());
                        transferScheduleDTO.setIdScheduleArrival(transfer.getId());
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

    /**
     * get all schedules for direct trip by all parameters
     *
     * @param scheduleDTO
     * @return
     * @throws ParseException
     */
    @Override
    @Transactional
    public List<ScheduleDTO> getDirectSchedulesFromDTOByStationsAndDatesAndTrain(ScheduleDTO scheduleDTO) throws
            ParseException, BusinessLogicException {
        List<Schedule> schedules = new ArrayList<>();
        Schedule schedule = new Schedule();
        Train train = trainService.getByName(scheduleDTO.getTrainName());
        Station stationDeparture = stationService.getByName(scheduleDTO.getStationDepartureName());
        Station stationArrival = stationService.getByName(scheduleDTO.getStationArrivalName());
        Date dateDeparture = Utils.parseToDate(scheduleDTO.getDateDeparture());
        Date dateArrival = Utils.parseToDate(scheduleDTO.getDateArrival());
        if (train == null || stationArrival == null || stationDeparture == null)
            throw new BusinessLogicException(ErrorCode.NULL_ELEMENTS.getMessage());

        if (scheduleDTO.getDateArrival().equals(scheduleDTO.getDateDeparture()))
            dateArrival = Utils.getNextDay(scheduleDTO.getDateDeparture());
        schedule.setTrain(train);
        schedule.setDateArrival(dateArrival);
        schedule.setDateDeparture(dateDeparture);
        schedule.setStationDeparture(stationDeparture);
        schedule.setStationArrival(stationArrival);
        schedules = scheduleDAO.getByStationsAndDatesAndTrains(schedule);

        return schedules.stream()
                .map(x -> modelMapper.map(x, ScheduleDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * get schedules by stations and date/dates
     *
     * @param scheduleDTO
     * @return
     * @throws ParseException
     */
    @Override
    @Transactional
    public List<ScheduleDTO> getDirectSchedulesFromDTOByStations(ScheduleDTO scheduleDTO) throws ParseException, BusinessLogicException {
        Station stationDepartureForDirectSchedule = stationService.getByName(scheduleDTO.getStationDepartureName());
        Station stationArrivalForDirectSchedule = stationService.getByName(scheduleDTO.getStationArrivalName());
        List<Schedule> schedules = new ArrayList<>();
        if (stationArrivalForDirectSchedule == null || stationDepartureForDirectSchedule == null)
            throw new BusinessLogicException(ErrorCode.NULL_ELEMENTS.getMessage());

        Schedule schedule = new Schedule();
        schedule.setStationDeparture(stationDepartureForDirectSchedule);
        schedule.setStationArrival(stationArrivalForDirectSchedule);
        Date dateDeparture = Utils.parseToDate(scheduleDTO.getDateDeparture());
        schedule.setDateDeparture(dateDeparture);
        if (!scheduleDTO.getDateArrival().isEmpty()) {
            Date dateArrival = Utils.parseToDate(scheduleDTO.getDateArrival());
            if (scheduleDTO.getDateArrival().equals(scheduleDTO.getDateDeparture()))
                dateArrival = Utils.getNextDay(scheduleDTO.getDateArrival());
            schedule.setDateArrival(dateArrival);
            schedules = scheduleDAO.getByStationsAndDates(schedule);
        } else schedules = scheduleDAO.getByStationsAndDate(schedule);
        log.info("FOUND SCHEDULES BY STATIONS AND DATE");


        return schedules.stream()
                .map(x -> modelMapper.map(x, ScheduleDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * get direct schedules by train and date/dates
     *
     * @param scheduleDTO
     * @return
     * @throws ParseException
     */
    @Override
    @Transactional
    public List<ScheduleDTO> getDirectSchedulesFromDTOByTrain(ScheduleDTO scheduleDTO) throws ParseException, BusinessLogicException {
        Train train = trainService.getByName(scheduleDTO.getTrainName());
        List<Schedule> schedules = new ArrayList<>();
        if (train == null)
            throw new BusinessLogicException(ErrorCode.NULL_ELEMENTS.getMessage());

        Schedule schedule = new Schedule();
        Date dateDeparture = Utils.parseToDate(scheduleDTO.getDateDeparture());
        schedule.setDateDeparture(dateDeparture);
        schedule.setTrain(train);
        if (!scheduleDTO.getDateArrival().isEmpty()) {
            Date dateArrival = Utils.parseToDate(scheduleDTO.getDateArrival());
            if (scheduleDTO.getDateArrival().equals(scheduleDTO.getDateDeparture()))
                dateArrival = Utils.getNextDay(scheduleDTO.getDateArrival());
            schedule.setDateArrival(dateArrival);
            schedules = scheduleDAO.getByTrainAndDates(schedule);
        } else schedules = scheduleDAO.getByTrainAndDate(schedule);
        log.info("FOUND SCHEDULES BY TRAIN AND DATE");


        return schedules.stream()
                .map(x -> modelMapper.map(x, ScheduleDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * get direct schedules by date/dates
     *
     * @param scheduleDTO
     * @return
     * @throws ParseException
     */
    @Override
    @Transactional
    public List<ScheduleDTO> getDirectSchedulesFromDTOByDates(ScheduleDTO scheduleDTO) throws ParseException {
        Date dateDeparture = Utils.parseToDate(scheduleDTO.getDateDeparture());
        List<Schedule> schedules;
        if (!scheduleDTO.getDateArrival().isEmpty()) {
            Date dateArrival = Utils.parseToDate(scheduleDTO.getDateArrival());
            if (scheduleDTO.getDateArrival().equals(scheduleDTO.getDateDeparture()))
                dateArrival = Utils.getNextDay(scheduleDTO.getDateArrival());
            schedules = scheduleDAO.getByDates(dateDeparture, dateArrival);
        } else schedules = scheduleDAO.getByDate(dateDeparture);

        return schedules.stream()
                .map(x -> modelMapper.map(x, ScheduleDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * get free seats in train
     *
     * @param schedule
     * @return
     */
    @Override
    @Transactional
    public List<SeatDTO> getFreeSeats(Schedule schedule) {
        Train train = schedule.getTrain();
        Set<Seat> allSeats = train.getSeats();
        List<Seat> bookingSeatsBySchedule = ticketService.getBookingSeatsBySchedule(schedule);
        List<SeatDTO> freeSeats = new ArrayList<>();
        if (!bookingSeatsBySchedule.isEmpty())
            for (Seat bookingSeat :
                    bookingSeatsBySchedule) {
                allSeats.stream().filter(seat -> !seat.equals(bookingSeat)).forEach(seat -> {
                    SeatDTO seatDTO = new SeatDTO();
                    seatDTO.setCarriage(seat.getCarriage());
                    seatDTO.setSeat(seat.getSeat());
                    freeSeats.add(seatDTO);
                });
            }
        else
            allSeats.stream().forEach(seat -> {
                SeatDTO seatDTO = new SeatDTO();
                seatDTO.setCarriage(seat.getCarriage());
                seatDTO.setSeat(seat.getSeat());
                freeSeats.add(seatDTO);
            });
        return freeSeats;
    }

    @Override
    @Transactional
    public SeatsDTO getSeats(Long id) throws BusinessLogicException {
        Schedule schedule = getById(id);
        if (schedule == null)
            throw new BusinessLogicException(ErrorCode.NULL_ELEMENTS.getMessage());

        Train train = schedule.getTrain();
        Set<Seat> seats = train.getSeats();
        List<Seat> bookingSeats = ticketService.getBookingSeatsBySchedule(schedule);
        Integer cntCarriage = Collections.max(seats.stream().map(x -> x.getCarriage()).collect(Collectors.toList()));
        List<SeatDTO> seatDTOList = bookingSeats.stream().map(x -> modelMapper.map(x, SeatDTO.class)).collect(Collectors.toList());
        SeatsDTO seatsDTO = new SeatsDTO();
        seatsDTO.setBookingSeats(seatDTOList);
        seatsDTO.setCntCarriages(cntCarriage);
        return seatsDTO;

    }
}

