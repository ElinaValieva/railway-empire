package com.elina.railwayApp.DAO;

import com.elina.railwayApp.model.Status;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusDAO<E extends Status> extends GenericDAO<E> {

    Status getByName(String name);
}

