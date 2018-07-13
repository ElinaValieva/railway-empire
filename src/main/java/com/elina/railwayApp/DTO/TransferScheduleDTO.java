package com.elina.railwayApp.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class TransferScheduleDTO implements Serializable {

    Long idScheduleDeparture;

    Long idScheduleArrival;

    String stationDepartureName;

    String stationIntermediateName;

    String stationArrivalName;

    String trainDepartureName;

    String trainArrivalName;

    String dateDeparture;

    String dateIntermediateDeparture;

    String dateIntermediateArrival;

    String dateArrival;

    Integer price;
}
