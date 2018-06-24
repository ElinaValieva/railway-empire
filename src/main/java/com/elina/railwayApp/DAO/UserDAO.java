package com.elina.railwayApp.DAO;

import com.elina.railwayApp.model.User;

import java.util.List;

public interface UserDAO {

    List<User> getUsers();

    void addUser(User user);

    void removeUser(User user);

    User findUserByFrom(User user);
}
