package com.elina.railwayApp.controller;

import com.elina.railwayApp.DTO.StationDTO;
import com.elina.railwayApp.configuration.common.URLs;
import com.elina.railwayApp.exception.BusinessLogicException;
import com.elina.railwayApp.service.StationService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j
@RestController
@RequestMapping(value = URLs.STATION)
public class StationController {

    @Autowired
    private StationService stationService;


    /**
     * GET ALL STATIONS
     */
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping(URLs.GET_STATIONS)
    public ResponseEntity<?> getStations() {
        List<StationDTO> stationDTOList = stationService.getAll();
        return ResponseEntity.ok(stationDTOList);
    }

    /**
     * ADD unique STATION
     *
     * @param stationDTO
     */

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PostMapping(URLs.CREATE_STATION)
    public void addStation(@RequestBody StationDTO stationDTO) throws BusinessLogicException {
        stationService.add(stationDTO);
    }

    /**
     * REMOVE STATION IF IT NOT USED (only change status of station)
     *
     * @param name
     */
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @DeleteMapping(URLs.DELETE_STATION)
    public void removeStation(@PathVariable String name) throws BusinessLogicException {
        stationService.delete(name);
    }


    /**
     * FIND STATION FOR UPDATE BY ID IF NOT USED
     *
     * @param stationDTO
     */
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PutMapping(URLs.UPDATE_STATION)
    public void updateStation(@RequestBody StationDTO stationDTO) throws BusinessLogicException {
        stationService.update(stationDTO);
    }
}

