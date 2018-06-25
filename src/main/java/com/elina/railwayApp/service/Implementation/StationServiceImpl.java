package com.elina.railwayApp.service.Implementation;

import com.elina.railwayApp.DAO.StationDAO;
import com.elina.railwayApp.model.Station;
import com.elina.railwayApp.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class StationServiceImpl implements StationService {

    @Autowired
    private StationDAO stationDAO;

    @Override
    @Transactional
    public void add(Station station) {
        stationDAO.add(station);
    }

    @Override
    @Transactional
    public void delete(Station station) {
        stationDAO.delete(station);
    }

    @Override
    @Transactional
    public void update(Station station) {
        stationDAO.update(station);
    }
}
