package com.elina.railwayApp.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ScheduleDTO implements Serializable {

    Long id;

    String stationDepartureName;

    String stationArrivalName;

    String trainName;

    String dateDeparture;

    String dateArrival;
}


