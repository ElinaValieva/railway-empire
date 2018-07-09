package com.elina.railwayApp.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class TrainDTO implements Serializable {
    String trainName;

    Integer cntCarriage;

    Integer cntSeats;
}
