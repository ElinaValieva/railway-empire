package com.elina.railwayApp.DAO;

import com.elina.railwayApp.model.Schedule;
import com.elina.railwayApp.model.Ticket;
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
}
