package com.elina.railwayApp.DAO;

import com.elina.railwayApp.model.Station;

import java.util.List;

public interface StationDAO {

    Station findById(Long id);

    void add(Station station);

    void delete(Long id);

    void update(Station station);

    List<Station> getAll();
}
