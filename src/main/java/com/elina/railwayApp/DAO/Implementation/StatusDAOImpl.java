package com.elina.railwayApp.DAO.Implementation;

import com.elina.railwayApp.DAO.StatusDAO;
import com.elina.railwayApp.model.Status;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StatusDAOImpl implements StatusDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Status getByName(String name) {
        return (Status) sessionFactory.getCurrentSession()
                .createQuery("from Status where statusName = :name")
                .setParameter("name", name)
                .uniqueResult();
    }
}
