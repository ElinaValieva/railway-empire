package com.elina.railwayApp.service.Implementation;

import com.elina.railwayApp.DAO.TicketDAO;
import com.elina.railwayApp.DTO.TicketDTO;
import com.elina.railwayApp.model.*;
import com.elina.railwayApp.service.ScheduleService;
import com.elina.railwayApp.service.SeatService;
import com.elina.railwayApp.service.TicketService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Log4j
@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketDAO ticketDAO;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private SeatService seatService;

    @Override
    @Transactional
    public void add(Ticket ticket) {
        ticketDAO.add(ticket);
    }

    @Override
    @Transactional
    public void add(TicketDTO ticketDTO, User user) {
        Schedule schedule = scheduleService.getById(ticketDTO.getSchedule());
        if (schedule != null && user != null) {
            if (checkUserUntilBooking(user, schedule)) {
                Train train = schedule.getTrain();
                Seat seat = seatService.getByTrainAndCarriageAndSeat(train, ticketDTO.getSeatDTO().getCarriage(), ticketDTO.getSeatDTO().getSeat());
                if (seat != null) {
                    Ticket ticket = new Ticket();
                    ticket.setSchedule(schedule);
                    ticket.setSeat(seat);
                    ticket.setUser(user);
                    add(ticket);
                    log.info("TICKET BOOKED WITH SUCCESS STATUS");
                } else log.warn("WRONG PARAMETERS.");
            } else log.warn("ALREADY BOOKED TICKET");
        }
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
}
