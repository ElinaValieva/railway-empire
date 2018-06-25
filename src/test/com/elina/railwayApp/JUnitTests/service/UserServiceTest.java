package com.elina.railwayApp.JUnitTests.service;

import com.elina.railwayApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class UserServiceTest {

    @Autowired
    private UserService userService;

    @org.junit.Test
    public void findByEmailTest() {
        assertNull(userService.findUserByEmailTest("elina@mail.ru"));
    }

    @org.junit.Test
    public void findUserByForm() {
    }
}