package com.elina.railwayApp.controller;

import com.elina.railwayApp.DTO.ChartDTO;
import com.elina.railwayApp.configuration.common.URLs;
import com.elina.railwayApp.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping(value = URLs.CHART)
public class ChartController {

    @Autowired
    private ChartService chartService;

    @GetMapping(URLs.GET_POPULAR_STATIONS)
    public ResponseEntity<?> getPopularStations(@PathVariable String dateFrom,
                                                @PathVariable String dateTo) throws ParseException {
        ChartDTO chartDTO = chartService.chartForPopularStations(dateFrom, dateTo);
        return ResponseEntity.ok(chartDTO);
    }
}
