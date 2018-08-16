package com.elina.railwayApp.DAO;

import com.elina.railwayApp.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDAO<E extends User> extends GenericDAO<E> {

    void updateProfile(User user);

    User findUserByEmail(String login);



}
