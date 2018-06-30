package com.elina.railwayApp.model;

import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    @Column(name = "id")
    private Long id;

    @Getter
    @Setter
    @Column(name = "date_arrival")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateArrival;

    @Getter
    @Setter
    @Column(name = "date_department")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDepartment;

    @Getter
    @Setter
    @OneToOne
    private Station stationArrival;

    @Getter
    @Setter
    @OneToOne
    private Station stationDepartment;

    @Getter
    @Setter
    @OneToOne
    private Train train;

}
