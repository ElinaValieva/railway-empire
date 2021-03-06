package com.elina.railwayApp.controller;

import com.elina.railwayApp.configuration.common.URLs;
import com.elina.railwayApp.configuration.common.Views;
import com.elina.railwayApp.service.SecureService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j
@Controller
public class ViewController {

    @Autowired
    private SecureService secureService;

    @RequestMapping(value = URLs.WELCOME)
    public String home() {
        return Views.WELCOME;
    }

    @RequestMapping(value = URLs.REGISTRATION)
    public String registration() {
        return Views.REGISTRATION;
    }

    @RequestMapping(value = URLs.LOGIN)
    public String login() {
        return Views.LOGIN;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = URLs.PROFILE)
    public String getProfile() {
        return Views.PROFILE;
    }

    @RequestMapping(value = URLs.SCHEDULE)
    public String test() {
        return Views.HEADER_SCHEDULE;
    }

    @RequestMapping(value = URLs.SEAT)
    public String seat() {
        return Views.SEAT;
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @RequestMapping(value = URLs.ADD_ITEM_RAILWAY)
    public String addItemsRailway() {
        return Views.ADD_ITEMS_RAILWAY;
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @RequestMapping(value = URLs.SHOW_ITEMS_RAILWAY)
    public String showItemsRailway() {
        return Views.MAP;
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @RequestMapping(value = URLs.EDIT_ITEMS_RAILWAY)
    public String editItemsRailway() {
        return Views.EDIT_ITEMS_RAILWAY;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = URLs.USERS_MAP)
    public String showUsersMap() {
        return Views.USERS_MAP;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = URLs.CHART)
    public String showChart() {
        return Views.CHART;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = URLs.SHOW_DELETED_ITEMS_RAILWAY)
    public String showDeletedItemsRailway() {
        return Views.DELETED_ITEMS_RAILWAY;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = URLs.TRIPS)
    public String showTrips() {
        return Views.TRIPS;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = URLs.LOGOUT)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        secureService.logOut(request, response);
        return Views.LOGIN;
    }
}
