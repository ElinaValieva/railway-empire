package com.elina.railwayApp.service;

import com.elina.railwayApp.model.Ticket;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TicketService {

    void add(Ticket ticket);

    void delete(Ticket ticket);

    void update(Ticket ticket);

    List<Ticket> getAll();

    Ticket getById(Long id);
}
