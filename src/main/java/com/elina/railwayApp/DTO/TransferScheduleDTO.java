package com.elina.railwayApp.DTO;

import com.elina.railwayApp.model.Seat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class TransferScheduleDTO implements Serializable {

    String stationDepartureName;

    String stationIntermediateName;

    String stationArrivalName;

    String trainDepartureName;

    String trainArrivalName;

    String dateDeparture;

    String dateIntermediateDeparture;

    String dateIntermediateArrival;

    String dateArrival;

    List<SeatDTO> freeSeatsDeparture;

    List<SeatDTO> freeSeatsArrival;
}
