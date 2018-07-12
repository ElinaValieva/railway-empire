package com.elina.railwayApp.service;

import com.elina.railwayApp.DTO.ChartDTO;
import com.elina.railwayApp.configuration.common.Utils;
import com.elina.railwayApp.model.Station;
import com.elina.railwayApp.model.Ticket;
import com.elina.railwayApp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    public ChartDTO chartForPopularStations(String dateFrom, String dateTo) throws ParseException {
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

        List<User> users = userService.getUsers();
        List<Integer> ages = new ArrayList<>();
        for (User user : users) {
            Date userBrthDay = dateFormat.parse(user.getBirthDay());
            Calendar now = Calendar.getInstance();
            Calendar brthDay = Calendar.getInstance();
            if (user.getBirthDay() != null && userBrthDay.before(new Date()))
                brthDay.setTime(userBrthDay);
            ages.add(now.get(Calendar.YEAR) - brthDay.get(Calendar.YEAR));
        }
        return ages;
    }

    /**
     * get count booked tickets by date
     *
     * @param date
     * @return
     */

    public ChartDTO chartCntTicketsForDay(Date date) {

        List<Ticket> tickets = ticketService.getByDate(date);
        List<Station> stations = stationService.getAllStations();
        List<String> stationName = new ArrayList<>();
        List<Integer> cntTickets = new ArrayList<>();
        stations.stream().forEach(station -> {
            Integer cnt = Long.valueOf(tickets.stream().filter(ticket -> ticket.getSchedule().getStationDeparture().equals(station)).count()).intValue();
            if (cnt != 0) {
                stationName.add(station.getName());
                cntTickets.add(cnt);
            }
        });

        ChartDTO chartDTO = new ChartDTO();
        chartDTO.setValues(cntTickets);
        chartDTO.setLabels(stationName);
        return chartDTO;
    }


    /**
     * TODO
     * get money for a day
     */

}
