package com.elina.railwayApp.model;

import com.elina.railwayApp.configuration.common.Tables;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = Tables.STATION_TABLE)
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    @Column(name = "id")
    private Long id;

    @Getter
    @Setter
    @Column(name = "name")
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Station)) return false;
        Station station = (Station) o;
        return Objects.equals(getId(), station.getId()) &&
                Objects.equals(getName(), station.getName());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getName());
    }
}
