package com.elina.railwayApp.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class UserDTO implements Serializable {
    String firstName;

    String lastName;

    String login;

    String password;

    String sex;

    String birthDay;
}
