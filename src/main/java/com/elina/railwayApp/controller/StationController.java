package com.elina.railwayApp.controller;

import com.elina.railwayApp.configuration.common.URLs;
import com.elina.railwayApp.model.Station;
import com.elina.railwayApp.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(name = URLs.STATION)
public class StationController {

    @Autowired
    private StationService stationService;

    @RequestMapping(name = "/add", method = RequestMethod.POST)
    public String addStation(@ModelAttribute("station") Station station) {
        stationService.add(station);
        return URLs.STATION;
    }

    @RequestMapping(name = URLs.DELETE_STATION, method = RequestMethod.DELETE)
    public void removeStation(@ModelAttribute("station") Station station) {
        stationService.delete(station);
    }

    @RequestMapping(name = URLs.UPDATE_STATION, method = RequestMethod.PUT)
    public void updateStation(@ModelAttribute("station") Station station) {
        stationService.update(station);
    }
}

