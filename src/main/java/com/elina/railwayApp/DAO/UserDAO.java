package com.elina.railwayApp.DAO;

import com.elina.railwayApp.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDAO {

    List<User> getAll();

    void add(User user);

    void remove(User user);

    void update(User user);

    void updateProfile(User user);

    User findUserByForm(User user);

    User findUserByEmail(User user);

    User findUserByEmail(String login);



}
