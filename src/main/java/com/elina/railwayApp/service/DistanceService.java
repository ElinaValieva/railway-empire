package com.elina.railwayApp.service;

import com.elina.railwayApp.model.Schedule;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public interface DistanceService {

    Integer calculatePrice(Schedule schedule);

    Date calculateDateArrival(Schedule schedule);
}
