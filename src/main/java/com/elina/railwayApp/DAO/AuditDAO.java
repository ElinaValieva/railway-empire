package com.elina.railwayApp.DAO;

import com.elina.railwayApp.model.Audit;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AuditDAO {

    void add(Audit audit);

    void delete(Audit audit);

    List<Audit> getAll();

    List<Audit> getByDate(Date date);
}
