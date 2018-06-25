package com.elina.railwayApp.controller;

import com.elina.railwayApp.configuration.common.URLs;
import com.elina.railwayApp.configuration.common.Views;
import com.elina.railwayApp.model.User;
import com.elina.railwayApp.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class ViewController {

    @Autowired
    private UserValidator userValidator;


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
    public String getProfile() {
        return Views.PROFILE;
    }

    @RequestMapping(value = URLs.STATION, method = RequestMethod.GET)
    public String getStation() { return Views.STATION; }


    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public String test(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "page";
        }
        return "login";
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public String test() {
        return "page";
    }

}
