package com.elina.railwayApp.controller;

import com.elina.railwayApp.configuration.common.URLs;
import com.elina.railwayApp.configuration.common.Utils;
import com.elina.railwayApp.configuration.common.Views;
import com.elina.railwayApp.model.Message;
import com.elina.railwayApp.model.Role;
import com.elina.railwayApp.model.User;
import com.elina.railwayApp.service.MailService;
import com.elina.railwayApp.service.RoleService;
import com.elina.railwayApp.service.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Log4j
@RestController
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MailService mailService;

    @RequestMapping(value = URLs.REGISTRATION, method = RequestMethod.POST)
    public ResponseEntity<?> registration(@RequestBody User user) throws IOException {
        log.info("PREPARING PROCESS FOR REGISTER");
        if (userService.findByEmail(user.getLogin()) == null && user != null) {
            Role role = roleService.getRole();
            Set<Role> roleSet = new HashSet<>();
            roleSet.add(role);
            user.setPassword(Utils.encodePassword(user.getPassword()));
            user.setRoles(roleSet);
            userService.add(user);
            log.info("REGISTRATION WAS SUCCESSFUL FOR USER login = " + user.getLogin());
            Message message = Message.createWelcomeMessage(user.getLogin());
            mailService.sendMimeMessage(message);
            return ResponseEntity.ok("registration success");
        }
        log.warn("REGISTRATION FAILED FOR PERSON WITH SAME LOGIN");
        return new ResponseEntity<>("registration failed", HttpStatus.UNAUTHORIZED);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = URLs.UPDATE_PROFILE, method = RequestMethod.POST)
    public String updateUser(@ModelAttribute("user") User user) {
        log.info("UPDATE PROFILE FOR USER login = " + user.getLogin());
        userService.updateProfile(user);
        return Views.PROFILE;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = URLs.SEND_MESSAGE, method = RequestMethod.GET)
    public String sendMessage(@ModelAttribute("user") User user) throws IOException {
        //test
        user.setLogin("veaufa@mail.ru");

        Message message = Message.createWelcomeMessage(user.getLogin());
        mailService.sendMimeMessage(message);
        return Views.WELCOME;
    }
}

