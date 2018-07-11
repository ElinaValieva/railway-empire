package com.elina.railwayApp.DAO;

import com.elina.railwayApp.model.Schedule;
import com.elina.railwayApp.model.Seat;
import com.elina.railwayApp.model.Ticket;
import com.elina.railwayApp.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketDAO {

    void add(Ticket ticket);

    void update(Ticket ticket);

    void delete(Ticket ticket);

    List<Ticket> getAll();

    Ticket getById(Long id);

    List<Ticket> getBySchedule(Schedule schedule);

    List<Ticket> findSameUserOnTrain(User user, Schedule schedule);

    Ticket findTicketByScheduleAndSeat(Schedule schedule, Seat seat);

    List<Ticket> getByUser(User user);
}
