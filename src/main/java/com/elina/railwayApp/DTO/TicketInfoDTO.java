package com.elina.railwayApp.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class TicketInfoDTO implements Serializable {

    String userFirstName;

    String userLastName;

    String userLogin;

    String userBirthDay;

    String userSex;

    Integer seatCarriage;

    Integer seatSeat;
}
