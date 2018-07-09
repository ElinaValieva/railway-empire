package com.elina.railwayApp.service;

import com.elina.railwayApp.exception.BusinessLogicException;
import com.elina.railwayApp.model.User;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

@Service
public interface UserService {

    void add(User user);

    void remove(User user);

    List<User> getUsers();

    User findByEmail(User user);

    User findByEmail(String login);

    User findUserByForm(User user);

    void update(User user);

    void updateProfile(User user);

    void registration(User user) throws IOException, BusinessLogicException, MessagingException;
}
