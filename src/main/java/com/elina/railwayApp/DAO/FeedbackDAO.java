package com.elina.railwayApp.DAO;

import com.elina.railwayApp.model.Feedback;

import java.util.List;

public interface FeedbackDAO {

    void add(Feedback feedback);

    void update(Feedback feedback);

    void delete(Feedback feedback);

    Feedback getById(Long id);

    List<Feedback> getAll();
}
