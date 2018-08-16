package com.elina.railwayApp.DAO.Implementation;

import com.elina.railwayApp.DAO.AuditDAO;
import com.elina.railwayApp.model.Audit;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class AuditDAOImpl<E extends Audit> extends GenericDAOImpl<E> implements AuditDAO<E> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Audit> getByDate(Date date) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Audit where date = :date")
                .setParameter("date", date)
                .getResultList();
    }
}
