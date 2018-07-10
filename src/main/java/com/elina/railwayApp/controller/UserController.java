package com.elina.railwayApp.controller;

import com.elina.railwayApp.DTO.UserDTO;
import com.elina.railwayApp.configuration.common.URLs;
import com.elina.railwayApp.configuration.common.Views;
import com.elina.railwayApp.exception.BusinessLogicException;
import com.elina.railwayApp.model.User;
import com.elina.railwayApp.service.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;

@Log4j
@RestController
public class UserController {


    @Autowired
    private UserService userService;


    @PutMapping(URLs.REGISTRATION)
    public void registration(@RequestBody UserDTO userDTO) throws BusinessLogicException, MessagingException, IOException {
        userService.registration(userDTO);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping(URLs.UPDATE_PROFILE)
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateProfile(user);
        return Views.PROFILE;
    }

}

