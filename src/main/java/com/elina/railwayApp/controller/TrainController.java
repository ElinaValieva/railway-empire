package com.elina.railwayApp.controller;

import com.elina.railwayApp.configuration.common.URLs;
import com.elina.railwayApp.configuration.common.Views;
import com.elina.railwayApp.model.Train;
import com.elina.railwayApp.service.TrainService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@Log4j
public class TrainController {


    @Autowired
    private TrainService trainService;

    /**
     * GET ALL TRAINS
     */
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @RequestMapping(value = URLs.GET_TRAINS, method = RequestMethod.GET)
    public String getTrains(Model model) {
        List<Train> trains = trainService.getAll();
        log.info("GET ALL TRAINS");
        model.addAttribute("trains", trains);
        return Views.TRAIN;
    }

    /**
     * CREATE unique TRAIN
     *
     * @param cntCarriage
     * @param cntSeats
     * @param train
     */
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @RequestMapping(value = URLs.CREATE_TRAIN, method = RequestMethod.POST)
    public String createTrain(@ModelAttribute("train") Train train, int cntCarriage, int cntSeats) {
        Train trainCreating = trainService.getByName(train.getName());
        if (trainCreating == null && cntCarriage > 0 && cntSeats > 0) {
            log.info("CREATE TRAIN number = " + train.getName());
            log.info("CNT carriage = " + cntCarriage + " CNT seats" + cntSeats);
            trainService.add(train, cntCarriage, cntSeats);
        }
        log.warn("CAN'T CREATE NOT UNIQUE TRAIN");
        return Views.TRAIN;
    }

    /**
     * REMOVE TRAIN IF IT ISN'T EXIST IN SCHEDULE
     */
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @RequestMapping(value = URLs.DELETE_TRAIN, method = RequestMethod.POST)
    public String removeTrain(@ModelAttribute("train") Train train) {
        log.info("PREPARE PROCESS TO REMOVE TRAIN number = " + train.getName());

        //TODO
        // condition with find in schedule
        if (true) {
            trainService.delete(train);
            log.info("TRAIN WAS REMOVED");
        } else log.warn("TRAIN WASN't REMOVED");
        return Views.TRAIN;
    }


}
