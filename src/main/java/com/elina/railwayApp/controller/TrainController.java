package com.elina.railwayApp.controller;

import com.elina.railwayApp.configuration.common.URLs;
import com.elina.railwayApp.configuration.common.Views;
import com.elina.railwayApp.model.Train;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Log4j
public class TrainController {

    /*
    @Autowired
    private TrainService trainService;
*/
    /*
    @RequestMapping(name = URLs.TRAIN, method = RequestMethod.GET)
    public String getTrains(Model model){
        List<Train> trains = trainService.getAll();
        model.addAttribute("trains", trains);
        return Views.TRAIN;
    }*/

    @RequestMapping(value= "/test/add", method = RequestMethod.POST)
    public String trains(@RequestBody Train train) {
        log.info("HELLO BEACH");
        return Views.TRAIN;
    }
}
