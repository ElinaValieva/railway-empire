package com.elina.railwayApp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import com.elina.railwayApp.configuration.common.Tables;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = Tables.USER_TABLE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @Column(name = "firstName")
    private String firstName;

    @Getter
    @Setter
    @Column(name = "lastName")
    private String lastName;

    @Getter
    @Setter
    @Column(name = "login")
    private String login;

    @Getter
    @Setter
    @Column(name = "password")
    private String password;

    @Getter
    @Setter
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = {
            @JoinColumn(name = "user_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id")
            })
    private Set<Role> roleSet;
}