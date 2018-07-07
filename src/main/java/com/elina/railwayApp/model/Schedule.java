package com.elina.railwayApp.model;

import com.elina.railwayApp.configuration.common.Tables;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = Tables.SCHEDULE_TABLE)
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "date_departure")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDeparture;


    @Column(name = "date_arrival")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateArrival;

    @OneToOne
    private Station stationDeparture;

    @OneToOne
    private Station stationArrival;

    @OneToOne
    private Train train;
}
