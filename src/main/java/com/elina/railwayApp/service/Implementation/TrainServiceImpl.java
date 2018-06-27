package com.elina.railwayApp.service.Implementation;

import com.elina.railwayApp.DAO.TrainDAO;
import com.elina.railwayApp.model.Train;
import com.elina.railwayApp.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TrainServiceImpl implements TrainService {

    @Autowired
    private TrainDAO trainDAO;

    @Override
    @Transactional
    public void add(Train train) {
        trainDAO.add(train);
    }

    @Override
    @Transactional
    public void delete(Train train) {
        trainDAO.delete(train);
    }

    @Override
    @Transactional
    public void update(Train train) {
        trainDAO.update(train);
    }

    @Override
    @Transactional
    public List<Train> getAll() {
        return trainDAO.getAll();
    }

    @Override
    @Transactional
    public Train getById(Long id) {
        return trainDAO.getById(id);
    }
}
