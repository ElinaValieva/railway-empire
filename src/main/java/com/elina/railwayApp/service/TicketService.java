package com.elina.railwayApp.service;

import com.elina.railwayApp.DTO.TicketDTO;
import com.elina.railwayApp.DTO.TicketInfoDTO;
import com.elina.railwayApp.exception.BusinessLogicException;
import com.elina.railwayApp.model.Schedule;
import com.elina.railwayApp.model.Seat;
import com.elina.railwayApp.model.Ticket;
import com.elina.railwayApp.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TicketService {

    void add(Ticket ticket);

    void add(TicketDTO ticketDTO, User user) throws BusinessLogicException;

    void delete(Ticket ticket);

    void update(Ticket ticket);

    List<Ticket> getAll();

    Ticket getById(Long id);

    List<Seat> getBookingSeatsBySchedule(Schedule schedule);

    boolean checkUserUntilBooking(User user, Schedule schedule);

    boolean checkSeatUntilBooking(Seat seat, Schedule schedule);

    boolean checkScheduleForAvailability(Schedule schedule);

    List<Ticket> getBySchedules(Schedule schedule);

    List<TicketInfoDTO> getByScheduleId(Long id);
}
