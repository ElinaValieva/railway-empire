package com.elina.railwayApp.DTO;

import com.elina.railwayApp.model.Station;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ScheduleDTO implements Serializable {
    Station stationDeparture;
    Station stationArrival;
    String date;
}
