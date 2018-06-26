package com.elina.railwayApp.service;

import com.elina.railwayApp.model.Station;

import java.util.List;

public interface StationService {

    void add(Station station);

    void delete(Long id);

    void update(Station station);

    List<Station> getAll();

    Station getById(Long id);
}
