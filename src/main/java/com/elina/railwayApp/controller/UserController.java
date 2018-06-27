package com.elina.railwayApp.controller;

import com.elina.railwayApp.configuration.common.URLs;
import com.elina.railwayApp.configuration.common.Utils;
import com.elina.railwayApp.configuration.common.Views;
import com.elina.railwayApp.model.Role;
import com.elina.railwayApp.model.User;
import com.elina.railwayApp.service.RoleService;
import com.elina.railwayApp.service.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashSet;
import java.util.Set;

@Log4j
@Controller
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = URLs.REGISTRATION, method = RequestMethod.POST)
    public String registration(@ModelAttribute("user") User user) {
        if (userService.findByEmail(user.getLogin()) == null) {
            Role role = roleService.getRole();
            Set<Role> roleSet = new HashSet<>();
            roleSet.add(role);
            user.setPassword(Utils.encodePassword(user.getPassword()));
            user.setRoles(roleSet);
            userService.add(user);
            log.info("Register user with username" + user.getLogin());
            return Views.LOGIN;
        }
        log.warn("Person with same login was added before");
        return Views.REGISTRATION;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = URLs.UPDATE_PROFILE, method = RequestMethod.POST)
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateProfile(user);
        return Views.PROFILE;
    }
}

