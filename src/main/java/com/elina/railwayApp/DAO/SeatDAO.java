package com.elina.railwayApp.DAO;

import com.elina.railwayApp.model.Seat;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatDAO {

    void add(Seat seat);

    void delete(Seat seat);

    void update(Seat seat);

    List<Seat> getAll();

    Seat getById(Long id);
}
