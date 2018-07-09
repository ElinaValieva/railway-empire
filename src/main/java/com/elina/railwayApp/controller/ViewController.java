package com.elina.railwayApp.controller;

import com.elina.railwayApp.configuration.common.URLs;
import com.elina.railwayApp.configuration.common.Views;
import com.elina.railwayApp.model.User;
import com.elina.railwayApp.service.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Log4j
@Controller
public class ViewController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = URLs.WELCOME)
    public String home(Model model) {
        model.addAttribute("startPage", "Hello beach!");
        model.addAttribute("message", "this is project from Elinaaas");
        return Views.WELCOME;
    }

    @RequestMapping(value = URLs.REGISTRATION, method = RequestMethod.GET)
    public String registration() {
        return Views.REGISTRATION;
    }

    @RequestMapping(value = URLs.LOGIN, method = RequestMethod.GET)
    public String login() {
        return Views.LOGIN;
    }

    @RequestMapping(value = URLs.UPDATE_PROFILE, method = RequestMethod.GET)
    public String getProfile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByEmail(userName);
        model.addAttribute("user", user);
        return Views.PROFILE;
    }

    @RequestMapping(value = URLs.TRAIN, method = RequestMethod.GET)
    public String train() {
        return Views.TRAIN;
    }

    @RequestMapping(value = URLs.STATION, method = RequestMethod.GET)
    public String station() {
        return Views.STATION;
    }

    @RequestMapping(value = URLs.SCHEDULE, method = RequestMethod.GET)
    public String schedule() {
        return Views.SCHEDULE;
    }

    @RequestMapping(value = URLs.SCHEDULE_CREATE, method = RequestMethod.GET)
    public String createSchedule() {
        return Views.CREATE_SCHEDULE;
    }

    @RequestMapping(value = "/test")
    public String test() {
        return "headerSchedule";
    }

    @RequestMapping(value = "/seat")
    public String seat() {
        return "seat";
    }

    @RequestMapping(value = "/addItems")
    public String addItemsRailway() {
        return "addItemsRailway";
    }
}
