package com.elina.railwayApp.service.Implementation;

import com.elina.railwayApp.DAO.TicketDAO;
import com.elina.railwayApp.model.Ticket;
import com.elina.railwayApp.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketDAO ticketDAO;

    @Override
    @Transactional
    public void add(Ticket ticket) {
        ticketDAO.add(ticket);
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
}
