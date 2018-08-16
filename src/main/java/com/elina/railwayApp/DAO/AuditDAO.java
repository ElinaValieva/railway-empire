package com.elina.railwayApp.DAO;

import com.elina.railwayApp.model.Audit;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AuditDAO<E extends Audit> extends GenericDAO<E> {

    List<Audit> getByDate(Date date);
}
