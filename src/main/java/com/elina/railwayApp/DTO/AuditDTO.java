package com.elina.railwayApp.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class AuditDTO implements Serializable {

    private Long id;

    private String date;

    private String userLogin;

    private String userFirstName;

    private String userLastName;

    private String operations;

    private String oldValue;

    private String newValue;
}
