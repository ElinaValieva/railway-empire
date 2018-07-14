package com.elina.railwayApp.DAO.Implementation;

import com.elina.railwayApp.DAO.FeedbackDAO;
import com.elina.railwayApp.model.Feedback;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FeedbackDAOImpl implements FeedbackDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Feedback feedback) {
        sessionFactory.getCurrentSession()
                .save(feedback);
    }

    @Override
    public void update(Feedback feedback) {
        sessionFactory.getCurrentSession()
                .update(feedback);
    }

    @Override
    public void delete(Feedback feedback) {
        sessionFactory.getCurrentSession()
                .delete(feedback);
    }

    @Override
    public Feedback getById(Long id) {
        return (Feedback) sessionFactory.getCurrentSession()
                .createQuery("from Feedback " +
                "where id = :id")
                .setParameter("id", id)
                .uniqueResult();
    }

    @Override
    public List<Feedback> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Feedback ")
                .getResultList();
    }
}
