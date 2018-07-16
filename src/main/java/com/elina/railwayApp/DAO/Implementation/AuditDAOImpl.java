package com.elina.railwayApp.DAO.Implementation;

import com.elina.railwayApp.DAO.AuditDAO;
import com.elina.railwayApp.model.Audit;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class AuditDAOImpl implements AuditDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Audit audit) {
        sessionFactory.getCurrentSession()
                .save(audit);
    }

    @Override
    public void delete(Audit audit) {
        sessionFactory.getCurrentSession()
                .delete(audit);
    }

    @Override
    public List<Audit> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Audit order by date desc ")
                .getResultList();
    }

    @Override
    public List<Audit> getByDate(Date date) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Audit where date = :date")
                .setParameter("date", date)
                .getResultList();
    }
}
