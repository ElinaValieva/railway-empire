package com.elina.railwayApp.service.Implementation;

import com.elina.railwayApp.DAO.FeedbackDAO;
import com.elina.railwayApp.model.Feedback;
import com.elina.railwayApp.service.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedBackService {

    @Autowired
    private FeedbackDAO feedbackDAO;

    @Override
    @Transactional
    public void add(Feedback feedback) {
        feedbackDAO.add(feedback);
    }

    @Override
    @Transactional
    public void delete(Feedback feedback) {
        feedbackDAO.delete(feedback);
    }

    @Override
    @Transactional
    public void update(Feedback feedback) {
        feedbackDAO.update(feedback);
    }

    @Override
    @Transactional
    public List<Feedback> getAll() {
        return feedbackDAO.getAll();
    }

    @Override
    @Transactional
    public Feedback getById(Long id) {
        return feedbackDAO.getById(id);
    }
}
