package com.elina.railwayApp.DAO.Implementation;

import com.elina.railwayApp.DAO.RoleDAO;
import com.elina.railwayApp.model.Role;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDAOImpl implements RoleDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Role getRole() {
        return (Role) sessionFactory.getCurrentSession()
                .createQuery("FROM Role where type = :ROLE")
                .setParameter("ROLE", "ROLE_USER")
                .uniqueResult();
    }
}

