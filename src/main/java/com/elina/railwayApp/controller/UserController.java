package com.elina.railwayApp.controller;

import com.elina.railwayApp.configuration.common.URLs;
import com.elina.railwayApp.configuration.common.Views;
import com.elina.railwayApp.exception.UserNotFoundException;
import com.elina.railwayApp.model.User;
import com.elina.railwayApp.service.UserService;
import com.sun.mail.smtp.SMTPSenderFailedException;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.IOException;

@Log4j
@RestController
public class UserController {


    @Autowired
    private UserService userService;


    @PostMapping(URLs.REGISTRATION)
    public ResponseEntity<?> registration(@RequestBody User user) {
        try {
            userService.registration(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (UserNotFoundException userNotFoundException) {
            userNotFoundException.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception messagingException) {
            messagingException.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping(URLs.UPDATE_PROFILE)
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateProfile(user);
        return Views.PROFILE;
    }

}

