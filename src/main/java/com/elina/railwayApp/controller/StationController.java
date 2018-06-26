package com.elina.railwayApp.controller;

import com.elina.railwayApp.configuration.common.URLs;
import com.elina.railwayApp.configuration.common.Views;
import com.elina.railwayApp.model.Station;
import com.elina.railwayApp.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StationController {

    @Autowired
    private StationService stationService;

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @RequestMapping(value = URLs.STATION, method = RequestMethod.GET)
    public String getStation(Model model) {
        model.addAttribute("stations", stationService.getAll());
        return Views.STATION;
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @RequestMapping(name = URLs.CREATE_STATION, method = RequestMethod.POST)
    public String addStation(@ModelAttribute("station") Station station) {
        stationService.add(station);
        return Views.STATION;
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @RequestMapping(value = {URLs.DELETE_STATION}, method = RequestMethod.GET)
    public String removeStation(@PathVariable String id) {
        stationService.delete(Long.valueOf(id));
        return Views.STATION;
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @RequestMapping(value = {URLs.STATION_UPDATE}, method = RequestMethod.GET)
    public String updateStation(@PathVariable String id, Model model) {
        Station station = stationService.getById(Long.valueOf(id));
        model.addAttribute("station", station);
        return Views.STATION;
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @RequestMapping(value = {URLs.UPDATE_STATION}, method = RequestMethod.POST)
    public String updateStation(@ModelAttribute("station") Station station) {
        stationService.update(station);
        return Views.STATION;
    }


}

