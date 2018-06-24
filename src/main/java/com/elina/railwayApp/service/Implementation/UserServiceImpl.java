package com.elina.railwayApp.service.Implementation;

import com.elina.railwayApp.DAO.UserDAO;
import com.elina.railwayApp.model.User;
import com.elina.railwayApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional
    public void addUser(User user) {
        userDAO.addUser(user);
    }

    @Override
    @Transactional
    public void removeUser(User user) {
        userDAO.removeUser(user);
    }

    @Override
    @Transactional
    public List<User> getUsers() {
        return userDAO.getUsers();
    }

    @Override
    @Transactional
    public User findByLogin(String login) {
        List<User> users = getUsers();
        User user = users.stream().filter(x->x.getLogin().equals(login)).collect(Collectors.toList()).get(0);
        return user;
    }

    @Override
    @Transactional
    public User findUser(User user) {
        return userDAO.findUserByFrom(user);
    }

}
