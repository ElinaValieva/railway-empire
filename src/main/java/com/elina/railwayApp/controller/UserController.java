package com.elina.railwayApp.controller;

import com.elina.railwayApp.configuration.common.URLs;
import com.elina.railwayApp.configuration.common.Views;
import com.elina.railwayApp.model.Role;
import com.elina.railwayApp.model.User;
import com.elina.railwayApp.service.RoleService;
import com.elina.railwayApp.service.UserService;
import com.elina.railwayApp.configuration.common.Utils;
import com.elina.railwayApp.validator.LoginValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {

    @Autowired
    private LoginValidator loginValidator;

    @Autowired
    private LoginValidator registrationValidator;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

/*
    @RequestMapping(value = URLs.LOGIN, method = RequestMethod.POST)
    public String login(@Valid @ModelAttribute("user") User user,
                        BindingResult bindingResult, Model model) {
        loginValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors() || userService.findUserByForm(user) == null) {
            return Views.LOGIN;
        }
        User foundedUser = userService.findByEmail(user);
        model.addAttribute(foundedUser);
        return Views.WELCOME;
    }*/

    @RequestMapping(value = URLs.REGISTRATION, method = RequestMethod.POST)
    public String registration(@ModelAttribute("user") User user, BindingResult bindingResult) {
        registrationValidator.validate(user, bindingResult);
        Role role = roleService.getRole();
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        user.setPassword(Utils.encodePassword(user.getPassword()));
        user.setRoleSet(roleSet);
        userService.add(user);
        return Views.LOGIN;
    }

    @RequestMapping(value = URLs.UPDATE_PROFILE, method = RequestMethod.POST)
    public User updateUser(@ModelAttribute("user") User user, BindingResult bindingResult) {
        registrationValidator.validate(user, bindingResult);

        userService.update(user);
        return user;
    }
}

