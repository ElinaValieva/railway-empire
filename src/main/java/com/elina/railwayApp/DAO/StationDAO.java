package com.elina.railwayApp.DAO;

import com.elina.railwayApp.model.Station;

public interface StationDAO {

    void add(Station station);

    void delete(Station station);

    void update(Station station);
}
