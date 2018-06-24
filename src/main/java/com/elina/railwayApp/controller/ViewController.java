package com.elina.railwayApp.controller;

import com.elina.railwayApp.model.User;
import com.elina.railwayApp.service.UserService;
import com.elina.railwayApp.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ViewController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/home")
    public ModelMap home() {
        ModelMap modelMap = new ModelMap("home");
        modelMap.addAttribute("startPage", "Hello beach!");
        modelMap.addAttribute("message", "this is project from Elinaaas");
        return modelMap;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelMap registration() {
        ModelMap modelMap = new ModelMap("registration");
        modelMap.addAttribute("user", new User());
        return modelMap;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("user") User user) {
       /* userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()){
            return modelMap;
        }*/
        userService.addUser(user);
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelMap login() {
        return new ModelMap("login");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute("user") User user) {
        if (userService.findUser(user) != null)
            return "home";
        return "login";
    }
}
