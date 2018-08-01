package com.elina.railwayApp.service.Implementation;

import com.elina.railwayApp.DAO.TicketDAO;
import com.elina.railwayApp.DTO.TicketDTO;
import com.elina.railwayApp.DTO.TicketInfoDTO;
import com.elina.railwayApp.configuration.common.Utils;
import com.elina.railwayApp.exception.BusinessLogicException;
import com.elina.railwayApp.exception.ErrorCode;
import com.elina.railwayApp.model.*;
import com.elina.railwayApp.service.*;
import com.itextpdf.text.DocumentException;
import lombok.extern.log4j.Log4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Log4j
@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketDAO ticketDAO;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private SeatService seatService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TicketBuilderPDF ticketBuilderPDF;

    @Autowired
    private DistanceService distanceService;


    @Override
    @Transactional
    public void add(Ticket ticket) {
        ticketDAO.add(ticket);
    }

    @Override
    @Transactional
    public void add(TicketDTO ticketDTO, User user) throws BusinessLogicException, DocumentException, MessagingException, IOException, ParseException {
        Schedule schedule = scheduleService.getById(ticketDTO.getSchedule());

        if (schedule == null || user == null)
            throw new BusinessLogicException(ErrorCode.NULL_ELEMENTS.getMessage());

        if (!checkUserUntilBooking(user, schedule))
            throw new BusinessLogicException(ErrorCode.TICKET_ALREADY_BOOKED.getMessage());

        if (!checkScheduleForAvailability(schedule))
            throw new BusinessLogicException(ErrorCode.TRAIN_WAS_ARRIVED.getMessage());

        Train train = schedule.getTrain();
        Seat seat = seatService.getByTrainAndCarriageAndSeat(train, ticketDTO.getSeatDTO().getCarriage(), ticketDTO.getSeatDTO().getSeat());

        if (seat == null || !checkSeatUntilBooking(seat, schedule))
            throw new BusinessLogicException(ErrorCode.NULL_ELEMENTS.getMessage());

        Ticket ticket = new Ticket();
        ticket.setSchedule(schedule);
        ticket.setSeat(seat);
        ticket.setUser(user);
        ticket.setPrice(distanceService.calculateDirectTripPrice(schedule));
        add(ticket);
        ticketBuilderPDF.createTicket(ticket);
        log.info("TICKET BOOKED WITH SUCCESS STATUS");
    }

    @Override
    @Transactional
    public void delete(Ticket ticket) {
        ticketDAO.delete(ticket);
    }

    @Override
    @Transactional
    public void update(Ticket ticket) {
        ticketDAO.update(ticket);
    }

    @Override
    @Transactional
    public List<Ticket> getAll() {
        return ticketDAO.getAll();
    }

    @Override
    @Transactional
    public Ticket getById(Long id) {
        return ticketDAO.getById(id);
    }

    @Override
    @Transactional
    public List<Seat> getBookingSeatsBySchedule(Schedule schedule) {
        List<Ticket> tickets = ticketDAO.getBySchedule(schedule);
        List<Seat> bookingSeats = new ArrayList<>();
        for (Ticket ticket :
                tickets) {
            bookingSeats.add(ticket.getSeat());
        }
        return bookingSeats;
    }

    @Override
    @Transactional
    public boolean checkUserUntilBooking(User user, Schedule schedule) {
        return ticketDAO.findSameUserOnTrain(user, schedule).isEmpty();
    }

    @Override
    @Transactional
    public boolean checkSeatUntilBooking(Seat seat, Schedule schedule) {
        return ticketDAO.findTicketByScheduleAndSeat(schedule, seat) == null;
    }

    @Override
    public boolean checkScheduleForAvailability(Schedule schedule) throws ParseException {
        Date date = schedule.getDateDeparture();
        return Utils.checkForCurrentDayForBookingTicket(date);
    }

    @Override
    @Transactional
    public List<Ticket> getBySchedules(Schedule schedule) {
        return ticketDAO.getBySchedule(schedule);
    }

    @Override
    @Transactional
    public List<TicketInfoDTO> getByScheduleId(Long id) {
        Schedule schedule = scheduleService.getById(id);
        List<Ticket> tickets = getBySchedules(schedule);
        return tickets.stream()
                .map(x -> modelMapper.map(x, TicketInfoDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<TicketInfoDTO> getByUser(User user) {
        List<Ticket> tickets = ticketDAO.getByUser(user);
        return tickets.stream()
                .map(x -> modelMapper.map(x, TicketInfoDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<Ticket> getByDate(Date date) {
        return ticketDAO.getByDate(date);
    }

    @Override
    public List<Ticket> getByDates(Date dateFrom, Date dateTo) {
        return ticketDAO.getByDates(dateFrom, dateTo);
    }
}
