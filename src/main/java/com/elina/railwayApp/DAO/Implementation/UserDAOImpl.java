package com.elina.railwayApp.DAO.Implementation;

import com.elina.railwayApp.DAO.UserDAO;
import com.elina.railwayApp.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl<E extends User> extends GenericDAOImpl<E> implements UserDAO<E> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void updateProfile(User user) {
        sessionFactory.getCurrentSession().createQuery("UPDATE User SET " +
                "firstName = :firstName, lastName = :lastName, login = :login, birthDay = :birthday, sex = :sex where id =: id")
                .setParameter("firstName", user.getFirstName())
                .setParameter("lastName", user.getLastName())
                .setParameter("login", user.getLogin())
                .setParameter("birthday", user.getBirthDay())
                .setParameter("sex", user.getSex())
                .setParameter("id", user.getId())
                .executeUpdate();
    }

    @Override
    public User findUserByEmail(String login) {
        return (User) sessionFactory.getCurrentSession()
                .createQuery("FROM User u where login = :login")
                .setParameter("login", login)
                .uniqueResult();
    }

}