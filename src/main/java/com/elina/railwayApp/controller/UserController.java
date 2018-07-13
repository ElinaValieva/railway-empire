package com.elina.railwayApp.controller;

import com.elina.railwayApp.DTO.AuditDTO;
import com.elina.railwayApp.DTO.TicketInfoDTO;
import com.elina.railwayApp.DTO.UserDTO;
import com.elina.railwayApp.configuration.common.URLs;
import com.elina.railwayApp.configuration.common.Views;
import com.elina.railwayApp.exception.BusinessLogicException;
import com.elina.railwayApp.model.User;
import com.elina.railwayApp.service.AuditService;
import com.elina.railwayApp.service.TicketService;
import com.elina.railwayApp.service.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

@Log4j
@RestController
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private AuditService auditService;

    @PutMapping(URLs.REGISTRATION)
    public void registration(@RequestBody UserDTO userDTO) throws BusinessLogicException, MessagingException, IOException {
        userService.registration(userDTO);
    }

    /*
    TODO - refactor
     */
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping(URLs.UPDATE_PROFILE)
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateProfile(user);
        return Views.PROFILE;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(URLs.SHOW_MARKERS_ON_USERS_MAP)
    public ResponseEntity<?> showTrips() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByEmail(userName);
        List<TicketInfoDTO> tickets = ticketService.getByUser(user);
        return ResponseEntity.ok(tickets);
    }

    @GetMapping(URLs.AUDIT)
    public ResponseEntity<?> getAuditInfo() {
        List<AuditDTO> auditDTOList = auditService.getAuditsInfo();
        return ResponseEntity.ok(auditDTOList);
    }

}

