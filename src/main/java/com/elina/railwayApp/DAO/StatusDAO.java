package com.elina.railwayApp.DAO;

import com.elina.railwayApp.model.Status;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusDAO {

    Status getByName(String name);
}

