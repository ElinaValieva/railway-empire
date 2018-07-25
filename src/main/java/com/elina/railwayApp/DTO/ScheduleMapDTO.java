package com.elina.railwayApp.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class ScheduleMapDTO implements Serializable {

    private Double stationDepartureLatitude;

    private Double stationDepartureLongitude;

    private Double stationArrivalLatitude;

    private Double stationArrivalLongitude;

    private String stationDepartureName;

    private String stationArrivalName;

    private String trainName;

    private Long durationInSeconds;

    private Double speed;
}
