package com.elina.railwayApp.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class SeatsDTO implements Serializable {

    List<SeatDTO> bookingSeats;

    Integer cntCarriages;

}
