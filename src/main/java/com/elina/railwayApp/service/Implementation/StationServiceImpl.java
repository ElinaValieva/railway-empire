package com.elina.railwayApp.service.Implementation;

import com.elina.railwayApp.DAO.StationDAO;
import com.elina.railwayApp.DAO.StatusDAO;
import com.elina.railwayApp.DTO.StationDTO;
import com.elina.railwayApp.exception.BusinessLogicException;
import com.elina.railwayApp.exception.ErrorCode;
import com.elina.railwayApp.model.Station;
import com.elina.railwayApp.model.Status;
import com.elina.railwayApp.service.StationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StationServiceImpl implements StationService {

    @Autowired
    private StationDAO stationDAO;

    @Autowired
    private StatusDAO statusDAO;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public void add(StationDTO stationDTO) throws BusinessLogicException {
        Station stationCreating = getByName(stationDTO.getName());
        if (stationCreating != null)
            throw new BusinessLogicException(ErrorCode.STATION_NOT_UNIQUE.getMessage());
        Station station = new Station();
        station.setName(stationDTO.getName());
        station.setLatitude(stationDTO.getLatitude());
        station.setLongitude(stationDTO.getLongitude());
        Status status = statusDAO.getByName("NOT_USED");
        station.setStatus(status);
        stationDAO.add(station);
    }

    @Override
    @Transactional
    public void delete(String name) throws BusinessLogicException {
        Station station = getByName(name);
        if (station == null)
            throw new BusinessLogicException(ErrorCode.NULL_ELEMENTS.getMessage());

        if (!station.getStatus().getStatusName().equals("NOT_USED"))
            throw new BusinessLogicException(ErrorCode.STATION_USED.getMessage());


        Status status = statusDAO.getByName("DELETED");
        station.setStatus(status);
        stationDAO.update(station);
    }

    @Override
    @Transactional
    public void update(StationDTO stationDTO) throws BusinessLogicException {
        Station station = getByName(stationDTO.getNewName());
        if (station != null)
            throw new BusinessLogicException(ErrorCode.STATION_NOT_UNIQUE.getMessage());
        station = getByName(stationDTO.getName());
        station.setName(stationDTO.getNewName());
        Status status = statusDAO.getByName("NOT_USED");
        station.setStatus(status);
        stationDAO.update(station);
    }

    @Override
    @Transactional
    public void reestablish(String name) {
        Station station = getByName(name);
        Status status = statusDAO.getByName("NOT_USED");
        station.setStatus(status);
        stationDAO.update(station);
    }

    @Override
    @Transactional
    public List<StationDTO> getAll() {
        List<Station> stations = stationDAO.getAll();
        return stations.stream().filter(x -> !x.getStatus().getStatusName().equals("DELETED"))
                .map(x -> modelMapper.map(x, StationDTO.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<Station> getAllStations() {
        return stationDAO.getAll();
    }

    @Override
    @Transactional
    public List<StationDTO> getAllDeletedStations() {
        List<Station> stations = stationDAO.getAll();
        return stations.stream().filter(x -> x.getStatus().getStatusName().equals("DELETED"))
                .map(x -> modelMapper.map(x, StationDTO.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Station getById(Long id) {
        return stationDAO.findById(id);
    }

    @Override
    @Transactional
    public Station getByName(String name) {
        return stationDAO.findByName(name);
    }
}
