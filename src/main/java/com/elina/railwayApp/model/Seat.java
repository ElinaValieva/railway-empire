package com.elina.railwayApp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "seat")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @Column(name = "carriage")
    private int carriage;

    @Getter
    @Setter
    @Column(name = "seat")
    private int seat;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "train_id", nullable = false)
    @Getter
    @Setter
    private Train train;
}
