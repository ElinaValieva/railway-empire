package com.elina.railwayApp.controller;

import com.elina.railwayApp.DTO.StationDTO;
import com.elina.railwayApp.DTO.TrainDTO;
import com.elina.railwayApp.DTO.TrainInfoDTO;
import com.elina.railwayApp.configuration.common.URLs;
import com.elina.railwayApp.exception.BusinessLogicException;
import com.elina.railwayApp.service.TrainService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j
public class TrainController {


    @Autowired
    private TrainService trainService;

    /**
     * GET ALL TRAINS
     */
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping(URLs.GET_TRAINS)
    public ResponseEntity<?> getTrains() {
        List<TrainInfoDTO> trains = trainService.getLastPositionTrain();
        trains.forEach(System.out::println);
        return ResponseEntity.ok(trains);
    }

    /**
     * CREATE unique TRAIN
     *
     * @param trainDTO
     */
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PutMapping(URLs.CREATE_TRAIN)
    public void createTrain(@RequestBody TrainDTO trainDTO) throws BusinessLogicException {
        trainService.add(trainDTO);
    }

    /**
     * REMOVE TRAIN IF IT ISN'T EXIST IN SCHEDULE
     *
     * @param id
     */
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @DeleteMapping(URLs.DELETE_TRAIN)
    public void removeTrain(@PathVariable Long id) throws BusinessLogicException {
        trainService.delete(id);
    }

}
