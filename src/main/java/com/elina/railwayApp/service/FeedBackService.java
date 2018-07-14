package com.elina.railwayApp.service;

import com.elina.railwayApp.model.Feedback;

import java.util.List;


public interface FeedBackService {

    void add(Feedback feedback);

    void delete(Feedback feedback);

    void update(Feedback feedback);

    List<Feedback> getAll();

    Feedback getById(Long id);
}
