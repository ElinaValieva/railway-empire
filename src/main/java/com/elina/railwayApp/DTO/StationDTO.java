package com.elina.railwayApp.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class StationDTO implements Serializable {
    String name;

    Double latitude;

    Double longitude;
}
