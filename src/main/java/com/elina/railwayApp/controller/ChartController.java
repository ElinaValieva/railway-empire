package com.elina.railwayApp.controller;

import com.elina.railwayApp.DTO.ChartDTO;
import com.elina.railwayApp.configuration.common.URLs;
import com.elina.railwayApp.exception.BusinessLogicException;
import com.elina.railwayApp.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping(value = URLs.CHART)
public class ChartController {

    @Autowired
    private ChartService chartService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(URLs.GET_POPULAR_STATIONS)
    public ResponseEntity<?> getPopularStations(@PathVariable String dateFrom,
                                                @PathVariable String dateTo) throws ParseException, BusinessLogicException {
        ChartDTO chartDTO = chartService.chartForPopularStations(dateFrom, dateTo);
        return ResponseEntity.ok(chartDTO);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(URLs.GET_STATISTICS_PASSENGERS)
    public ResponseEntity<?> getAgesStatistics() throws ParseException {
        List<Integer> ages = chartService.chartForUsersAge();
        return ResponseEntity.ok(ages);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(URLs.GET_CNT_TICKETS)
    public ResponseEntity<?> getCntTickets(@PathVariable String date) throws ParseException, BusinessLogicException {
        ChartDTO chartDTO = chartService.chartCntTicketsForDay(date);
        return ResponseEntity.ok(chartDTO);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(URLs.GET_PROFIT_FROM_TICKETS)
    public ResponseEntity<?> getProfitFromBooking(@PathVariable String dateFrom,
                                                  @PathVariable String dateTo) throws ParseException, BusinessLogicException {
        ChartDTO chartDTO = chartService.chartGetProfit(dateFrom, dateTo);
        return ResponseEntity.ok(chartDTO);
    }
}
