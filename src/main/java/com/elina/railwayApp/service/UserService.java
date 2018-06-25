package com.elina.railwayApp.service;

import com.elina.railwayApp.model.User;

import java.util.List;

public interface UserService {

    void add(User user);

    void remove(User user);

    List<User> getUsers();

    User findByEmail(User user);

    User findByEmail(String login);

    User findUserByForm(User user);

    User update(User user);


}
