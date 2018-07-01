package com.elina.railwayApp.model;

import com.elina.railwayApp.configuration.common.Tables;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = Tables.ROLE_TABLE)
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "type")
    private String type;
}
