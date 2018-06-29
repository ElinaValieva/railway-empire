package com.elina.railwayApp.service;

import com.elina.railwayApp.model.Station;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StationService {

    void add(Station station);

    void delete(Long id);

    void update(Station station);

    List<Station> getAll();

    Station getById(Long id);

    Station getByName(String name);
}
