package com.elina.railwayApp.service;

import com.elina.railwayApp.DTO.StationDTO;
import com.elina.railwayApp.DTO.TrainDTO;
import com.elina.railwayApp.DTO.TrainInfoDTO;
import com.elina.railwayApp.exception.BusinessLogicException;
import com.elina.railwayApp.model.Train;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TrainService {

    void add(TrainDTO trainDTO) throws BusinessLogicException;

    void delete(Long id) throws BusinessLogicException;

    void update(Train train);

    List<TrainDTO> getAll();

    Train getById(Long id);

    Train getByName(String name);

    List<TrainInfoDTO> getLastPositionTrain();
}
