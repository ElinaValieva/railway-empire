package com.elina.railwayApp.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class TrainInfoDTO implements Serializable {
    String name;

    String stationName;

    String dateDeparture;

    String dateArrival;

    Double latitude;

    Double longitude;

    Integer cntCarriage;

    Integer cntSeats;
}
