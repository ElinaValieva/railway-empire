package com.elina.railwayApp.service;

import com.elina.railwayApp.DTO.TrainDTO;
import com.elina.railwayApp.DTO.TrainInfoDTO;
import com.elina.railwayApp.exception.BusinessLogicException;
import com.elina.railwayApp.model.Train;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TrainService {

    void add(TrainDTO trainDTO) throws BusinessLogicException;

    void delete(String name) throws BusinessLogicException;

    void update(TrainDTO trainDTO) throws BusinessLogicException;

    void reestablish(String name);

    List<TrainDTO> getAll();

    List<Train> getAllTrains();

    Train getById(Long id);

    Train getByName(String name);

    List<TrainInfoDTO> getLastPositionTrain();

    List<TrainDTO> getAllDeletedTrains();
}
