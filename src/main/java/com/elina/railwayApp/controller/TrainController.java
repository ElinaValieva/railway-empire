package com.elina.railwayApp.controller;

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
@RequestMapping(value = URLs.TRAIN)
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
        return ResponseEntity.ok(trains);
    }

    /**
     * GET ALL TRAINS
     */
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping(URLs.GET_ALL_TRAINS)
    public ResponseEntity<?> getAllTrains() {
        List<TrainDTO> trains = trainService.getAll();
        return ResponseEntity.ok(trains);
    }

    /**
     * CREATE unique TRAIN
     *
     * @param trainDTO
     */
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PostMapping(URLs.CREATE_TRAIN)
    public void createTrain(@RequestBody TrainDTO trainDTO) throws BusinessLogicException {
        trainService.add(trainDTO);
    }

    /**
     * REMOVE TRAIN IF IT ISN'T EXIST IN SCHEDULE
     *
     * @param name
     */
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @DeleteMapping(URLs.DELETE_TRAIN)
    public void removeTrain(@PathVariable String name) throws BusinessLogicException {
        trainService.delete(name);
    }

    /**
     * UPDATE TRAINS NAME
     *
     * @param trainDTO
     * @throws BusinessLogicException
     */
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PutMapping(URLs.UPDATE_TRAIN)
    public void updateTrain(@RequestBody TrainDTO trainDTO) throws BusinessLogicException {
        trainService.update(trainDTO);
    }

    /**
     * RETURN ALL DELETED TRAINS
     * @return
     */
    @GetMapping(URLs.DELETED_TRAINS)
    public ResponseEntity<?> getAllDeletedTrains() {
        List<TrainDTO> trainDTOList = trainService.getAllDeletedTrains();
        return ResponseEntity.ok(trainDTOList);
    }

    /**
     * REESTABLISH TRAIN
     * @param name
     */
    @GetMapping(URLs.REESTABLISH_TRAIN)
    public void reestablishTrain(@PathVariable String name){
        trainService.reestablish(name);
    }
}
