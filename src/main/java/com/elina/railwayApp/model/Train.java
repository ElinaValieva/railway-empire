package com.elina.railwayApp.model;


import com.elina.railwayApp.configuration.common.Tables;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = Tables.TRAIN_TABLE)
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "train", cascade = CascadeType.ALL)
    private Set<Seat> seats;

    @OneToOne
    private Status status;
}
