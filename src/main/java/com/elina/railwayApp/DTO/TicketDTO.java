package com.elina.railwayApp.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class TicketDTO implements Serializable {
    Long schedule;

    SeatDTO seatDTO;
}
