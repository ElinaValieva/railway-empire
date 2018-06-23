package com.elina.railwayApp.service;

import com.elina.railwayApp.model.User;

import java.util.List;

public interface UserService {

    void addUser(User user);

    void removeUser(User user);

    List<User> getUsers();
}
