package com.elina.railwayApp.service;

import com.elina.railwayApp.model.Schedule;
import org.springframework.stereotype.Component;

import java.awt.geom.Point2D;
import java.util.Date;

@Component
public class DistanceService {
    private final static Integer SPEED_TRAIN = 60;
    private final static Integer EARTH_RADIUS = 6371;
    private final static Integer DATE = 1000 * 60 * 60 * 24;
    private final static Integer HOURS = 1000 * 60 * 60;
    private final static Double RADIANS = Math.PI / 180;
    private final static Double K_PRICE = 5.0;
    private final static Double K_DAY = 62.35;

    /**
     * PRICE = DISTANCE * K1 - (CURRENT_DATE - DATE_DEPARTURE) * K2;
     *
     * @param schedule
     * @return
     */
    public Integer calculatePrice(Schedule schedule) {
        Date date = new Date();
        Point2D pointDeparture = new Point2D.Double(schedule.getStationDeparture().getLatitude(), schedule.getStationDeparture().getLongitude());
        Point2D pointArrival = new Point2D.Double(schedule.getStationArrival().getLatitude(), schedule.getStationArrival().getLongitude());
        Double distance = distance(pointDeparture, pointArrival);
        /*
        count dates between
         */
        Long deltaDates = (schedule.getDateDeparture().getTime() - date.getTime()) / DATE;
        return (int) (distance * K_PRICE - deltaDates * K_DAY);
    }

    /**
     * DATE_ARRIVAL = DATE_DEPARTURE + DISTANCE / SPEED_TRAIN;
     *
     * @param schedule
     * @return
     */
    public Date calculateDateArrival(Schedule schedule) {
        Date newDate = new Date();
        Date dateDeparture = schedule.getDateDeparture();
        Point2D pointDeparture = new Point2D.Double(schedule.getStationDeparture().getLatitude(), schedule.getStationDeparture().getLongitude());
        Point2D pointArrival = new Point2D.Double(schedule.getStationArrival().getLatitude(), schedule.getStationArrival().getLongitude());
        Double distance = distance(pointDeparture, pointArrival);
        /*
        time in milliseconds
         */
        Long time = Double.valueOf(distance * HOURS / SPEED_TRAIN).longValue();
        newDate.setTime(dateDeparture.getTime() + time);
        return newDate;
    }

    /**
     * Formula gaversinusov with modification for antipodes
     *
     * @param pointA
     * @param pointB
     * @return distance in kilometers
     */
    private Double distance(Point2D pointA, Point2D pointB) {
        Double latitudeA = RADIANS * pointA.getX();
        Double latitudeB = RADIANS * pointB.getX();
        Double longitudeA = RADIANS * pointA.getY();
        Double longitudeB = RADIANS * pointB.getY();

        // вычисления длины большого круга
        Double y = Math.sqrt(Math.pow(Math.cos(latitudeB) * Math.sin(longitudeB - longitudeA), 2)
                + Math.pow(Math.cos(latitudeA) * Math.sin(latitudeB) - Math.sin(latitudeA) * Math.cos(latitudeB) * Math.cos(longitudeB - longitudeA), 2));
        Double x = Math.sin(latitudeA) * Math.sin(latitudeB) + Math.cos(latitudeA) * Math.cos(latitudeB) * Math.cos(longitudeB - longitudeA);
        Double dist = Math.atan2(y, x) * EARTH_RADIUS;

        return dist;
    }
}
