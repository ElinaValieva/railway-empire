package com.elina.railwayApp.service;

import com.elina.railwayApp.DTO.UserDTO;
import com.elina.railwayApp.exception.BusinessLogicException;
import com.elina.railwayApp.model.User;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@Service
public interface UserService {

    void add(User user);

    void remove(User user);

    List<User> getUsers();

    User findByEmail(User user);

    User findByEmail(String login);

    UserDTO findUserByForm(UserDTO userDTO) throws BusinessLogicException;

    void update(User user);

    void updateProfile(UserDTO userDTO, User user) throws ParseException, BusinessLogicException;

    void registration(UserDTO userDTO) throws IOException, BusinessLogicException, MessagingException;
}
