package com.elina.railwayApp.DAO.Implementation;

import com.elina.railwayApp.DAO.TrainDAO;
import com.elina.railwayApp.model.Train;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TrainDAOImpl implements TrainDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Train train) {
        sessionFactory.getCurrentSession()
                .save(train);
    }

    @Override
    public void update(Train train) {
        sessionFactory.getCurrentSession()
                .update(train);
    }

    @Override
    public void delete(Train train) {
        sessionFactory.getCurrentSession()
                .delete(train);
    }

    @Override
    public List<Train> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Train")
                .getResultList();
    }

    @Override
    public Train getById(Long id) {
        return (Train) sessionFactory.getCurrentSession()
                .createQuery("from Train where id = :id")
                .setParameter("id", id)
                .uniqueResult();
    }

    @Override
    public Train getByName(String name) {
        return (Train) sessionFactory.getCurrentSession()
                .createQuery("from Train where name = :name")
                .setParameter("name", name)
                .uniqueResult();
    }
}
