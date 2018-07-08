package com.elina.railwayApp.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class ScheduleDTO implements Serializable {

    String stationDepartureName;

    String stationArrivalName;

    String trainName;

    String dateDeparture;

    String dateArrival;

//TODO    List<SeatDTO> freeSeats;
}


