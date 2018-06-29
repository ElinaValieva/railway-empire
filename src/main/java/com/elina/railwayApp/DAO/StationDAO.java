package com.elina.railwayApp.DAO;

import com.elina.railwayApp.model.Station;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StationDAO {

    Station findById(Long id);

    void add(Station station);

    void delete(Long id);

    void update(Station station);

    List<Station> getAll();

    Station findByName(String name);
}
