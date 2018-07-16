package com.elina.railwayApp.service;

import com.elina.railwayApp.DAO.UserDAO;
import com.elina.railwayApp.model.User;
import com.elina.railwayApp.service.Implementation.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    private User user;

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private UserServiceImpl userService;

    @Before
    public void setUp() throws Exception {
    user = new User();
    user.setId(47L);
    user.setFirstName("Super");
    user.setLastName("User");
    }
        @Test
    public void add() {
        userDAO.add(user);
        userService.add(user);
    }

    @Test
    public void remove() {
        userService.remove(user);
        userDAO.remove(user);
    }

    @Test
    public void getUsers() {
        when(userDAO.getAll()).thenReturn(new ArrayList<>());
        userService.getUsers();
        verify(userDAO).getAll();
    }

    @Test
    public void findByEmail() {
        when(userDAO.findUserByEmail(user)).thenReturn(new User());
        userService.findByEmail(user);
        verify(userDAO).findUserByEmail(user);
    }

    @Test
    public void findByEmail1() {
        when(userDAO.findUserByEmail("login@mail.ru")).thenReturn(new User());
        userService.findByEmail("login@mail.ru");
        verify(userDAO).findUserByEmail("login@mail.ru");
    }

    @Test
    public void update() {
        userDAO.update(user);
        userService.update(user);
    }
}