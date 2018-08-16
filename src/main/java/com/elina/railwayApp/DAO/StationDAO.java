package com.elina.railwayApp.DAO;

import com.elina.railwayApp.model.Station;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StationDAO<E extends Station> extends GenericDAO<E> {

    Station findByName(String name);
}
