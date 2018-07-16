package com.elina.railwayApp.DTO;

import com.elina.railwayApp.configuration.common.Utils;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.text.ParseException;

@Data
@NoArgsConstructor
public class ScheduleDTO implements Serializable {
    Long id;

    String stationDepartureName;

    String stationArrivalName;

    String trainName;

    String dateDeparture;

    String dateArrival;

    Integer price;

    public String convertDate() throws ParseException {
        dateDeparture = Utils.parseToDateTimeNewFormat(dateDeparture).toString();
        return dateDeparture;
    }
}


