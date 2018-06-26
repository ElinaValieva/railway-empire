package com.elina.railwayApp.service.Implementation;

import com.elina.railwayApp.DAO.UserDAO;
import com.elina.railwayApp.configuration.common.Utils;
import com.elina.railwayApp.model.User;
import com.elina.railwayApp.service.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Log4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional
    public void add(User user) {
        userDAO.add(user);
    }

    @Override
    @Transactional
    public void remove(User user) {
        userDAO.remove(user);
    }

    @Override
    @Transactional
    public List<User> getUsers() {
        return userDAO.getAll();
    }

    @Override
    @Transactional
    public User findByEmail(User user) {
        return userDAO.findUserByEmail(user);
    }

    @Override
    @Transactional
    public User findByEmail(String login) {
        return userDAO.findUserByEmail(login);
    }

    @Override
    @Transactional
    public User findUserByForm(User user) {
        return userDAO.findUserByForm(user);
    }

    @Override
    @Transactional
    public void update(User user) {
        userDAO.update(user);
    }

    @Override
    @Transactional
    public void updateProfile(User user) {
        String password = user.getPassword();
        user.setPassword(Utils.encodePassword(password));
        userDAO.updateProfile(user);
    }

}