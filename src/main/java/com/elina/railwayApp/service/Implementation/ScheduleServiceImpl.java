package com.elina.railwayApp.service.Implementation;

import com.elina.railwayApp.DAO.ScheduleDAO;
import com.elina.railwayApp.DAO.StatusDAO;
import com.elina.railwayApp.DTO.*;
import com.elina.railwayApp.configuration.common.Utils;
import com.elina.railwayApp.exception.BusinessLogicException;
import com.elina.railwayApp.exception.ErrorCode;
import com.elina.railwayApp.model.*;
import com.elina.railwayApp.service.*;
import lombok.extern.log4j.Log4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.TimeoutException;
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

    @Autowired
    private DistanceService distanceService;

    @Autowired
    private AuditService auditService;

    @Autowired
    private MessageQueueService messageQueueService;


    private static final int MIN_DELTA_TRANSFER = 15;
    private static final int MAX_DELTA_TRANSFER = 360;

    /**
     * create schedule (check intersection of times and correctness of times)
     *
     * @param scheduleDTO
     */
    @Override
    @Transactional
    public void add(ScheduleDTO scheduleDTO) throws ParseException, BusinessLogicException, IOException, TimeoutException {
        Train train = trainService.getByName(scheduleDTO.getTrainName());
        Station stationArrival = stationService.getByName(scheduleDTO.getStationArrivalName());
        Station stationDeparture = stationService.getByName(scheduleDTO.getStationDepartureName());

        if (stationArrival == null || stationDeparture == null || train == null)
            throw new BusinessLogicException(ErrorCode.NULL_ELEMENTS.getMessage());

        Date dateDeparture = Utils.parseToDateTime(scheduleDTO.getDateDeparture());

        Date dateArrival;
        if (scheduleDTO.getDateArrival().isEmpty())
            dateArrival = distanceService.calculateDateArrival(dateDeparture, stationDeparture, stationArrival);
        else dateArrival = Utils.parseToDateTime(scheduleDTO.getDateArrival());

        Schedule schedule = new Schedule();
        schedule.setStationDeparture(stationDeparture);
        schedule.setStationArrival(stationArrival);
        schedule.setDateDeparture(dateDeparture);
        schedule.setDateArrival(dateArrival);
        schedule.setTrain(train);


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
        schedule.getTrain().setStatus(status);

        scheduleDAO.add(schedule);
        auditService.createScheduleAuditInfo(schedule);
        messageQueueService.produceMsg("create id=" + schedule.getId());
        log.info("SCHEDULE WAS CREATED!");
    }

    @Override
    @Transactional
    public void delete(Long id) throws BusinessLogicException, IOException, TimeoutException {
        Schedule schedule = getById(id);
        if (!ticketService.getBySchedules(schedule).isEmpty())
            throw new BusinessLogicException(ErrorCode.SCHEDULE_IS_AVAILABLE.getMessage());
        scheduleDAO.delete(schedule);
        auditService.deleteScheduleAuditInfo(schedule);
        messageQueueService.produceMsg("delete id=" + schedule.getId());
    }

    @Override
    @Transactional
    public void update(ScheduleDTO scheduleDTO) throws BusinessLogicException, ParseException, IOException, TimeoutException {
        Schedule schedule = getById(scheduleDTO.getId());
        Schedule scheduleOld = schedule;
        if (!ticketService.getBySchedules(schedule).isEmpty())
            throw new BusinessLogicException(ErrorCode.SCHEDULE_IS_AVAILABLE.getMessage());

        Train train = trainService.getByName(scheduleDTO.getTrainName());
        Station stationDeparture = stationService.getByName(scheduleDTO.getStationDepartureName());
        Station stationArrival = stationService.getByName(scheduleDTO.getStationArrivalName());

        if (train == null || stationDeparture == null || stationArrival == null)
            throw new BusinessLogicException(ErrorCode.NULL_ELEMENTS.getMessage());

        Date dateDeparture = Utils.parseToDateTime(scheduleDTO.getDateDeparture());
        Date dateArrival = Utils.parseToDateTime(scheduleDTO.getDateArrival());

        train.setName(scheduleDTO.getTrainName());
        stationDeparture.setName(scheduleDTO.getStationDepartureName());
        stationArrival.setName(scheduleDTO.getStationArrivalName());
        schedule.setTrain(train);
        schedule.setStationDeparture(stationDeparture);
        schedule.setStationArrival(stationArrival);
        schedule.setDateDeparture(dateDeparture);
        schedule.setDateArrival(dateArrival);

        if (stationDeparture.equals(stationArrival))
            throw new BusinessLogicException(ErrorCode.SAME_STATIONS.getMessage());

        if (!dateDeparture.before(dateArrival))
            throw new BusinessLogicException(ErrorCode.WRONG_DATES.getMessage());

        if (getByDateAndTrainToCheckIntersection(schedule).size() > 1)
            throw new BusinessLogicException(ErrorCode.INTERSECTION_SCHEDULES.getMessage());

        if (Utils.checkCurrentDay(dateDeparture))
            throw new BusinessLogicException(ErrorCode.SCHEDULE_FOR_CURRENT_DAY.getMessage());

        scheduleDAO.update(schedule);
        auditService.updateScheduleAuditInfo(scheduleOld, schedule);
        messageQueueService.produceMsg("update id=" + schedule.getId());
    }

    @Override
    @Transactional
    public List<ScheduleDTO> getAll() {
        List<Schedule> schedules = scheduleDAO.getAll();
        return schedules.stream()
                .map(x -> modelMapper.map(x, ScheduleDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<ScheduleDTO> getAllForToday() throws ParseException {
        List<Schedule> schedules = scheduleDAO.getForToday();
        return schedules.stream()
                .map(x -> modelMapper.map(x, ScheduleDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Schedule getById(Long id) {
        return (Schedule) scheduleDAO.getById(id);
    }

    @Override
    @Transactional
    public List<Schedule> getByDate(Date dateDeparture) {
        return scheduleDAO.getByDate(dateDeparture);
    }

    @Override
    @Transactional
    public List<Schedule> getByDates(Date dateDeparture, Date dateArrival) throws BusinessLogicException {
        dateArrival = Utils.getNextDay(dateArrival);
        if (dateArrival.before(dateDeparture))
            throw new BusinessLogicException(ErrorCode.WRONG_DATES.getMessage());

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
    public Map<Station, List<Schedule>> getTransferList(String dateTransferDeparture, String dateTransferArrival) throws ParseException, BusinessLogicException {
        Date dateDeparture = Utils.parseToDate(dateTransferDeparture);
        Date dateArrival;
        if (dateTransferArrival != null)
            dateArrival = Utils.parseToDate(dateTransferArrival);
        else
            dateArrival = Utils.getNextDay(dateTransferArrival);

        if (dateArrival.before(dateDeparture))
            throw new BusinessLogicException(ErrorCode.WRONG_DATES.getMessage());

        List<Schedule> schedules = getByDates(dateDeparture, dateArrival);


        Map<Station, List<Schedule>> mapStationsForTransfer = new HashMap<>();
        for (Schedule schedule : schedules) {
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
    public List<TransferScheduleDTO> getTransferSchedules(ScheduleDTO scheduleDTO) throws ParseException, BusinessLogicException {
        Map<Station, List<Schedule>> mapStationForTransfer = getTransferList(scheduleDTO.getDateDeparture(), scheduleDTO.getDateArrival());
        Station stationDeparture = stationService.getByName(scheduleDTO.getStationDepartureName());
        Station stationArrival = stationService.getByName(scheduleDTO.getStationArrivalName());
        List<Schedule> schedules = mapStationForTransfer.get(stationDeparture);
        List<TransferScheduleDTO> transferScheduleDTOS = new ArrayList<>();
        for (Schedule schedule : schedules) {
            if (!schedule.getStationArrival().equals(stationArrival)) {
                List<Schedule> transferSchedule = mapStationForTransfer.get(schedule.getStationArrival());
                if (transferSchedule != null) {
                    transferSchedule = transferSchedule.stream()
                            .filter(x -> x.getStationArrival().equals(stationArrival)
                                    && schedule.getDateArrival().before(x.getDateDeparture()) ||
                                    Utils.checkTransfer(schedule.getDateArrival(), x.getDateDeparture(), MIN_DELTA_TRANSFER, MAX_DELTA_TRANSFER))
                            .collect(Collectors.toList());

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
                        try {
                            transferScheduleDTO.setPrice(distanceService.calculateTransferTripPrice(schedule, transfer));
                        } catch (ParseException e) {
                            transferScheduleDTO.setPrice(0);
                        }
                        transferScheduleDTOS.add(transferScheduleDTO);
                    });
                }
            }
        }
        return transferScheduleDTOS;
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
        List<Schedule> schedules;
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

        if (dateArrival.before(dateDeparture))
            throw new BusinessLogicException(ErrorCode.WRONG_DATES.getMessage());

        schedule.setTrain(train);
        schedule.setDateArrival(dateArrival);
        schedule.setDateDeparture(dateDeparture);
        schedule.setStationDeparture(stationDeparture);
        schedule.setStationArrival(stationArrival);
        schedules = scheduleDAO.getByStationsAndDatesAndTrains(schedule);

        return mapping(schedules);
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
        List<Schedule> schedules;

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

            if (dateArrival.before(dateDeparture))
                throw new BusinessLogicException(ErrorCode.WRONG_DATES.getMessage());

            schedule.setDateArrival(dateArrival);
            schedules = scheduleDAO.getByStationsAndDates(schedule);
        } else schedules = scheduleDAO.getByStationsAndDate(schedule);
        log.info("FOUND SCHEDULES BY STATIONS AND DATE");


        return mapping(schedules);
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
        List<Schedule> schedules;
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

            if (dateArrival.before(dateDeparture))
                throw new BusinessLogicException(ErrorCode.WRONG_DATES.getMessage());

            schedule.setDateArrival(dateArrival);
            schedules = scheduleDAO.getByTrainAndDates(schedule);
        } else schedules = scheduleDAO.getByTrainAndDate(schedule);
        log.info("FOUND SCHEDULES BY TRAIN AND DATE");


        return mapping(schedules);
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
    public List<ScheduleDTO> getDirectSchedulesFromDTOByDates(ScheduleDTO scheduleDTO) throws ParseException, BusinessLogicException {
        Date dateDeparture = Utils.parseToDate(scheduleDTO.getDateDeparture());
        List<Schedule> schedules;
        if (!scheduleDTO.getDateArrival().isEmpty()) {
            Date dateArrival = Utils.parseToDate(scheduleDTO.getDateArrival());

            if (scheduleDTO.getDateArrival().equals(scheduleDTO.getDateDeparture()))
                dateArrival = Utils.getNextDay(scheduleDTO.getDateArrival());

            if (dateArrival.before(dateDeparture))
                throw new BusinessLogicException(ErrorCode.WRONG_DATES.getMessage());

            schedules = scheduleDAO.getByDates(dateDeparture, dateArrival);
        } else schedules = scheduleDAO.getByDate(dateDeparture);
        return mapping(schedules);
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

    @Override
    @Transactional
    public List<Schedule> getByStationArrivalAndDates(Station station, Date dateFrom, Date dateTo) {
        return scheduleDAO.getByStationArrivalAndDates(station, dateFrom, dateTo);
    }

    @Override
    @Transactional
    public List<ScheduleMapDTO> getByRealTimeSchedules() throws ParseException {
        List<Schedule> schedules = scheduleDAO.getRealTimeSchedules();
        return schedules
                .stream()
                .map(x -> {
                    ScheduleMapDTO scheduleMapDTO = new ScheduleMapDTO();
                    scheduleMapDTO.setTrainName(x.getTrain().getName());
                    try {
                        scheduleMapDTO.setDurationInSeconds(distanceService.getTimeInTripRealTime(x));
                    } catch (ParseException e) {
                        scheduleMapDTO.setDurationInSeconds(0l);
                    }
                    scheduleMapDTO.setSpeed(distanceService.getSpeed(x));
                    scheduleMapDTO.setStationDepartureName(x.getStationDeparture().getName());
                    scheduleMapDTO.setStationArrivalName(x.getStationArrival().getName());
                    scheduleMapDTO.setStationArrivalLatitude(x.getStationArrival().getLatitude());
                    scheduleMapDTO.setStationArrivalLongitude(x.getStationArrival().getLongitude());
                    scheduleMapDTO.setStationDepartureLatitude(x.getStationDeparture().getLatitude());
                    scheduleMapDTO.setStationDepartureLongitude(x.getStationDeparture().getLongitude());
                    return scheduleMapDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ScheduleDTO getByIdScheduleDTO(Long id) {
        return modelMapper.map(getById(id), ScheduleDTO.class);
    }

    public List<ScheduleDTO> mapping(List<Schedule> schedules) {
        return schedules.stream()
                .map(x -> modelMapper.map(x, ScheduleDTO.class))
                .map(x -> {
                    Integer price = 0;
                    try {
                        price = distanceService.calculateDirectTripPrice(x);
                    } catch (ParseException e) {
                        log.error(e.getMessage());
                        e.printStackTrace();
                    }
                    x.setPrice(price);
                    return x;
                })
                .collect(Collectors.toList());
    }
}

