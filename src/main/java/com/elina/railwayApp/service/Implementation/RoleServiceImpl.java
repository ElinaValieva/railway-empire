package com.elina.railwayApp.service.Implementation;

import com.elina.railwayApp.DAO.RoleDAO;
import com.elina.railwayApp.model.Role;
import com.elina.railwayApp.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDAO roleDAO;

    @Override
    @Transactional
    public Role getRole() {
        return roleDAO.getRole();
    }
}