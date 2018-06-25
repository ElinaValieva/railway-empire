package com.elina.railwayApp.DAO;

import com.elina.railwayApp.model.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDAO {

    Role getRole();
}
