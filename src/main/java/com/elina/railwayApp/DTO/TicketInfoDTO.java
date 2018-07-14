package com.elina.railwayApp.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class TicketInfoDTO implements Serializable {

    Integer id;

    String userFirstName;

    String userLastName;

    String userLogin;

    String userBirthDay;

    String userSex;

    String scheduleStationDepartureName;

    String scheduleStationArrivalName;

    String scheduleDateDeparture;

    String scheduleTrainName;

    String scheduleDateArrival;

    Double scheduleStationDepartureLatitude;

    Double scheduleStationDepartureLongitude;

    Double scheduleStationArrivalLatitude;

    Double scheduleStationArrivalLongitude;

    Integer seatCarriage;

    Integer seatSeat;

    Integer price;
}
