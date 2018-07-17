package com.elina.railwayApp.controller;

import com.elina.railwayApp.DTO.AuditDTO;
import com.elina.railwayApp.DTO.TicketInfoDTO;
import com.elina.railwayApp.DTO.UserDTO;
import com.elina.railwayApp.configuration.common.URLs;
import com.elina.railwayApp.exception.BusinessLogicException;
import com.elina.railwayApp.model.Ticket;
import com.elina.railwayApp.model.User;
import com.elina.railwayApp.service.*;
import com.itextpdf.text.DocumentException;
import lombok.extern.log4j.Log4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.text.ParseException;
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

    @Autowired
    private TicketBuilderPDF ticketBuilderPDF;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(URLs.REGISTRATION)
    public void registration(@RequestBody UserDTO userDTO) throws BusinessLogicException, MessagingException, IOException {
        userService.registration(userDTO);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(URLs.GET_PROFILE)
    public ResponseEntity<?> getProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByEmail(userName);
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return ResponseEntity.ok(userDTO);
    }


    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping(URLs.UPDATE_PROFILE)
    public void updateUser(@RequestBody UserDTO userDTO) throws ParseException, BusinessLogicException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByEmail(userName);
        userService.updateProfile(userDTO, user);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(URLs.GET_USERS_TICKETS)
    public ResponseEntity<?> showTrips() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByEmail(userName);
        List<TicketInfoDTO> tickets = ticketService.getByUser(user);
        return ResponseEntity.ok(tickets);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(URLs.AUDIT)
    public ResponseEntity<?> getAuditInfo() {
        List<AuditDTO> auditDTOList = auditService.getAuditsInfo();
        return ResponseEntity.ok(auditDTOList);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(URLs.DOWNLOAD)
    public void downloadTicket(HttpServletResponse response,
                               @PathVariable Long id) throws IOException, DocumentException {
        Ticket ticket = ticketService.getById(id);
        File file = ticketBuilderPDF.createPDF(ticket);
        String mimeType = URLConnection.guessContentTypeFromName(file.getName());
        response.setContentType(mimeType);
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));
        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        FileCopyUtils.copy(inputStream, response.getOutputStream());
    }
}

