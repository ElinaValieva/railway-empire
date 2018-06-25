package com.elina.railwayApp.DAO;

import com.elina.railwayApp.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {

    List<User> getAll();

    void add(User user);

    void remove(User user);

    void update(User user);

    User findUserByForm(User user);

    User findUserByEmail(User user);

    User findUserByEmail(String login);
}
