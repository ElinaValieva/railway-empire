package com.elina.railwayApp.service.Implementation;

import com.elina.railwayApp.model.Schedule;
import com.elina.railwayApp.service.DistanceService;
import org.springframework.stereotype.Service;

import java.awt.geom.Point2D;
import java.util.Date;

@Service
public class DistanceServiceImpl implements DistanceService {

    private final static Integer SPEED_TRAIN = 60;
    private final static Integer EARTH_RADIUS = 6371;
    private final static Integer DATE = 1000 * 60 * 60;
    private final static Double K_PRICE = 5.0;
    private final static Double K_DAY = 62.35;

    /**
     * PRICE = DISTANCE * K1 - (CURRENT_DATE - DATE_DEPARTURE) * K2;
     *
     * @param schedule
     * @return
     */
    @Override
    public Integer calculatePrice(Schedule schedule) {
        Date date = new Date();
        Point2D pointDeparture = new Point2D.Double(schedule.getStationDeparture().getLatitude(), schedule.getStationDeparture().getLongitude());
        Point2D pointArrival = new Point2D.Double(schedule.getStationArrival().getLatitude(), schedule.getStationArrival().getLongitude());
        Double distance = distance(pointDeparture, pointArrival);
        /*
        count dates between
         */
        Long deltaDates = (schedule.getDateDeparture().getTime() - date.getTime()) / (DATE * 24);
        return (int) (distance * K_PRICE - deltaDates * K_DAY);
    }

    /**
     * DATE_ARRIVAL = DATE_DEPARTURE + DISTANCE / SPEED_TRAIN;
     *
     * @param schedule
     * @return
     */
    @Override
    public Date calculateDateArrival(Schedule schedule) {
        Date newDate = new Date();
        Date dateDeparture = schedule.getDateDeparture();
        Point2D pointDeparture = new Point2D.Double(schedule.getStationDeparture().getLatitude(), schedule.getStationDeparture().getLongitude());
        Point2D pointArrival = new Point2D.Double(schedule.getStationArrival().getLatitude(), schedule.getStationArrival().getLongitude());
        Double distance = distance(pointDeparture, pointArrival);
        /*
        time in milliseconds
         */
        Long time = Double.valueOf(distance * DATE / SPEED_TRAIN).longValue();
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
        Double latitudeA = Math.PI * pointA.getX() / 180;
        Double latitudeB = Math.PI * pointB.getX() / 180;
        Double longitudeA = Math.PI * pointA.getY() / 180;
        Double longitudeB = Math.PI * pointB.getY() / 180;

        // вычисления длины большого круга
        Double y = Math.sqrt(Math.pow(Math.cos(latitudeB) * Math.sin(longitudeB - longitudeA), 2)
                + Math.pow(Math.cos(latitudeA) * Math.sin(latitudeB) - Math.sin(latitudeA) * Math.cos(latitudeB) * Math.cos(longitudeB - longitudeA), 2));
        Double x = Math.sin(latitudeA) * Math.sin(latitudeB) + Math.cos(latitudeA) * Math.cos(latitudeB) * Math.cos(longitudeB - longitudeA);
        Double dist = Math.atan2(y, x) * EARTH_RADIUS;

        return dist;
    }
}
