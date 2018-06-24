package com.elina.railwayApp.DAO.Implementation;

import com.elina.railwayApp.DAO.UserDAO;
import com.elina.railwayApp.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<User> getUsers() {
        return sessionFactory.getCurrentSession().createQuery("from User").getResultList();
    }

    @Override
    public void addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void removeUser(User user) {
        sessionFactory.getCurrentSession().remove(user);
    }

    @Override
    public User findUserByFrom(User user) {
        List<User> users = sessionFactory.getCurrentSession()
                .createQuery("FROM User where login = :login and password = :password")
                .setParameter("login", user.getLogin())
                .setParameter("password", user.getPassword())
                .getResultList();
        return users.get(0);
    }
}