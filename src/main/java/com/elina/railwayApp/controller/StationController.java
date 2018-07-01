package com.elina.railwayApp.controller;

import com.elina.railwayApp.configuration.common.URLs;
import com.elina.railwayApp.configuration.common.Views;
import com.elina.railwayApp.model.Station;
import com.elina.railwayApp.service.StationService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Log4j
@Controller
public class StationController {

    @Autowired
    private StationService stationService;


    /**
     * GET ALL STATIONS
     */
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @RequestMapping(value = URLs.GET_STATIONS, method = RequestMethod.GET)
    public String getStations(Model model) {
        log.info("GET ALL STATIONS");
        model.addAttribute("stations", stationService.getAll());
        return Views.STATION;
    }

    /**
     * ADD unique STATION
     *
     * @param station with id, name
     */

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @RequestMapping(name = URLs.CREATE_STATION, method = RequestMethod.POST)
    public String addStation(@ModelAttribute("station") Station station) {
        Station stationCreating = stationService.getByName(station.getName());
        if (stationCreating == null) {
            log.info("CREATE STATION WITH name = " + station.getName());
            stationService.add(station);
        }
        log.warn("CAN'T CREATE NOT UNIQUE STATION");
        return Views.STATION;
    }

    /**
     * REMOVE STATION IF IT NOT USED (only change status of station)
     */
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @RequestMapping(value = {URLs.DELETE_STATION}, method = RequestMethod.GET)
    public String removeStation(@PathVariable Long id) {
        Station station = stationService.getById(id);
        if (station != null
                && station.getStatus().getStatusName().equals("NOT_USED")) {
            stationService.delete(station);
        } else log.warn("STATION NOT FOUNDED or USED IN SCHEDULE");
        return Views.STATION;
    }

    /**
     * FIND STATION FOR UPDATE BY ID IF NOT USED
     */
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @RequestMapping(value = {URLs.STATION_UPDATE}, method = RequestMethod.GET)
    public String updateStation(@PathVariable Long id, Model model) {
        Station station = stationService.getById(id);
        if (station != null
                && station.getStatus().getStatusName().equals("NOT_USED")) {
            stationService.update(station);
            model.addAttribute("station", station);
        } else log.warn("CAN'T UPDATE WORKING STATION");
        return Views.STATION;
    }

    /**
     * UPDATE STATION NAME
     */
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @RequestMapping(value = {URLs.UPDATE_STATION}, method = RequestMethod.POST)
    public String updateStation(@ModelAttribute("station") Station station) {
        log.info("UPDATE STATION");
        stationService.update(station);
        return Views.STATION;
    }
}

