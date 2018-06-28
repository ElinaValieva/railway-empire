package com.elina.railwayApp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "schedule")
public class Schedule {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "train_id", nullable = false)
    private Train train;

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
}
