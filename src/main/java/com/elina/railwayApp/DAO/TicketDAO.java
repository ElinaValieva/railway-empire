package com.elina.railwayApp.DAO;

import com.elina.railwayApp.model.Schedule;
import com.elina.railwayApp.model.Seat;
import com.elina.railwayApp.model.Ticket;
import com.elina.railwayApp.model.User;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TicketDAO<E extends Ticket> extends GenericDAO<E> {

    List<Ticket> getBySchedule(Schedule schedule);

    List<Ticket> findSameUserOnTrain(User user, Schedule schedule);

    Ticket findTicketByScheduleAndSeat(Schedule schedule, Seat seat);

    List<Ticket> getByUser(User user);

    List<Ticket> getByDate(Date date);

    List<Ticket> getByDates(Date dateFrom, Date dateTo);
}
