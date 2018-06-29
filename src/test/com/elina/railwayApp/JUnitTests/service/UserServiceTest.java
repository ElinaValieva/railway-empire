package com.elina.railwayApp.JUnitTests.service;

import com.elina.railwayApp.model.User;
import com.elina.railwayApp.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void findByEmailTest() {
       // assertNull(userService.findUserByEmailTest("elina@mail.ru"));
    }

    @Test
    public void findUserByForm() {
    }

    @Test
    public void getUsers(){
        assertNotNull(userService.getUsers());
    }

    @Test
    public void addUser(){
        User user = new User();
        user.setFirstName("vasya");
        user.setLastName("pupkin");
        user.setLogin("login");
        user.setPassword("pass");
        userService.add(user);
    }
}