package com.elina.railwayApp.service;

import com.elina.railwayApp.DTO.StationDTO;
import com.elina.railwayApp.exception.BusinessLogicException;
import com.elina.railwayApp.model.Station;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StationService {

    void add(StationDTO stationDTO) throws BusinessLogicException;

    void delete(String name) throws BusinessLogicException;

    void update(StationDTO stationDTO) throws BusinessLogicException;

    void reestablish(String name);

    List<StationDTO> getAll();

    List<Station> getAllStations();

    List<StationDTO> getAllDeletedStations();

    Station getById(Long id);

    Station getByName(String name);

    List<String> getStationsName();
}
