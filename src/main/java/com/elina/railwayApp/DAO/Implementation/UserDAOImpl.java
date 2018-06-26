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
    public List<User> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from User").getResultList();
    }

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void remove(User user) {
        sessionFactory.getCurrentSession().remove(user);
    }

    @Override
    public void update(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    @Override
    public void updateProfile(User user) {
        sessionFactory.getCurrentSession().createQuery("UPDATE User SET " +
                "firstName = :firstName, lastName = :lastName, login = :login, password = :password, birthDay = :birthday, sex = :sex where id =: id")
                .setParameter("firstName", user.getFirstName())
                .setParameter("lastName", user.getLastName())
                .setParameter("login", user.getLogin())
                .setParameter("password", user.getPassword())
                .setParameter("birthday", user.getBirthDay())
                .setParameter("sex", user.getSex())
                .setParameter("id", user.getId())
                .executeUpdate();
    }

    @Override
    public User findUserByForm(User user) {
        return (User) sessionFactory.getCurrentSession()
                .createQuery("FROM User where login = :login and password = :password")
                .setParameter("login", user.getLogin())
                .setParameter("password", user.getPassword())
                .uniqueResult();
    }

    @Override
    public User findUserByEmail(User user) {
        return (User) sessionFactory.getCurrentSession()
                .createQuery("FROM User where login = :login")
                .setParameter("login", user.getLogin())
                .uniqueResult();
    }

    @Override
    public User findUserByEmail(String login) {
        return (User) sessionFactory.getCurrentSession()
                .createQuery("FROM User where login = :login")
                .setParameter("login", login)
                .uniqueResult();
    }

}