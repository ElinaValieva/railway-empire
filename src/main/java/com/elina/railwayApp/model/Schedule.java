package com.elina.railwayApp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
    private String dateArrival;

    @Getter
    @Setter
    @Column(name = "date_department")
    private String dateDepartment;

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
