package com.elina.railwayApp.service;

import com.elina.railwayApp.DTO.ChartDTO;
import com.elina.railwayApp.configuration.common.Utils;
import com.elina.railwayApp.exception.BusinessLogicException;
import com.elina.railwayApp.exception.ErrorCode;
import com.elina.railwayApp.model.Station;
import com.elina.railwayApp.model.Ticket;
import com.elina.railwayApp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ChartService {

    @Autowired
    private StationService stationService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private UserService userService;

    @Autowired
    private TicketService ticketService;


    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    /**
     * more popular stations between dates in schedule
     *
     * @param dateFrom
     * @param dateTo
     * @return
     */
    public ChartDTO chartForPopularStations(String dateFrom, String dateTo) throws ParseException, BusinessLogicException {
        if (dateFrom == null || dateTo == null)
            throw new BusinessLogicException(ErrorCode.NULL_ELEMENTS.getMessage());
        Date dateDeparture = Utils.parseToDate(dateFrom);
        Date dateArrival = Utils.parseToDate(dateTo);
        List<Station> stations = stationService.getAllStations();
        List<Integer> cntVisits = new ArrayList<>();
        List<String> stationsName = new ArrayList<>();
        stations.stream().forEach(station -> {
            Integer cnt = scheduleService.getByStationArrivalAndDates(station, dateDeparture, dateArrival).size();
            cntVisits.add(cnt);
            stationsName.add(station.getName());
        });
        ChartDTO chartDTO = new ChartDTO();
        chartDTO.setValues(cntVisits);
        chartDTO.setLabels(stationsName);
        return chartDTO;
    }

    /**
     * get ages passengers
     */
    public List<Integer> chartForUsersAge() throws ParseException {

        List<User> users = userService.getUsers()
                .stream()
                .filter(user -> user.getBirthDay() != null)
                .filter(user -> !user.getBirthDay().isEmpty())
                .collect(Collectors.toList());
        List<Integer> ages = new ArrayList<>();
        for (User user : users) {
            Date userBrthDay = dateFormat.parse(user.getBirthDay());
            if (userBrthDay.before(new Date())) {
                Calendar now = Calendar.getInstance();
                Calendar brthDay = Calendar.getInstance();
                brthDay.setTime(userBrthDay);
                ages.add(now.get(Calendar.YEAR) - brthDay.get(Calendar.YEAR));
            }
        }
        return ages;
    }

    /**
     * get count booked tickets by date
     *
     * @param dateFrom
     * @return
     */

    public ChartDTO chartCntTicketsForDay(String dateFrom) throws ParseException, BusinessLogicException {
        if (dateFrom == null)
            throw new BusinessLogicException(ErrorCode.WRONG_DATES.getMessage());
        Date date = Utils.parseToDate(dateFrom);
        List<Ticket> tickets = ticketService.getByDate(date);
        List<Station> stations = stationService.getAllStations();
        List<String> stationName = new ArrayList<>();
        List<Integer> cntTickets = new ArrayList<>();
        stations.stream().forEach(station -> {
            Integer cnt = Long.valueOf(tickets.stream().filter(ticket -> ticket.getSchedule().getStationDeparture().equals(station)).count()).intValue();
            stationName.add(station.getName());
            cntTickets.add(cnt);

        });

        ChartDTO chartDTO = new ChartDTO();
        chartDTO.setValues(cntTickets);
        chartDTO.setLabels(stationName);
        return chartDTO;
    }


    /**
     * return profit from booking tickets by days
     *
     * @param dateFrom
     * @param dateTo
     * @return
     * @throws BusinessLogicException
     * @throws ParseException
     */
    public ChartDTO chartGetProfit(String dateFrom, String dateTo) throws BusinessLogicException, ParseException {
        if (dateFrom == null || dateTo == null)
            throw new BusinessLogicException(ErrorCode.NULL_ELEMENTS.getMessage());
        Date dateDeparture = Utils.parseToDate(dateFrom);
        Date dateArrival = Utils.parseToDate(dateTo);
        List<Integer> profits = new ArrayList();
        List<String> dates = new ArrayList<>();
        for (Date date = dateDeparture; date.before(dateArrival); date = Utils.getNextDay(date)) {
            Integer profit = ticketService.getByDate(date)
                    .stream().mapToInt(ticket -> ticket.getPrice()).sum();
            profits.add(profit);
            dates.add(date.toString());
        }
        ChartDTO chartDTO = new ChartDTO();
        chartDTO.setLabels(dates);
        chartDTO.setValues(profits);
        return chartDTO;
    }

}
