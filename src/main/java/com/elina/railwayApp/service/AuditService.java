package com.elina.railwayApp.service;

import com.elina.railwayApp.model.Schedule;
import com.elina.railwayApp.model.Station;
import com.elina.railwayApp.model.Train;
import org.springframework.stereotype.Service;

@Service
public interface AuditService {

    void createTrainAuditInfo(Train train);

    void deleteTrainAuditInfo(Train train);

    void updateTrainAuditInfo(String oldValue, String newValue);

    void reestablishTrainAuditInfo(Train train);

    void createStationAuditInfo(Station station);

    void deleteStationAuditInfo(Station station);

    void updateStationAuditInfo(String oldValue, String newValue);

    void reestablishStationAuditInfo(Station station);

    void createScheduleAuditInfo(Schedule schedule);

    void deleteScheduleAuditInfo(Schedule schedule);

    void updateScheduleAuditInfo(Schedule oldSchedule, Schedule newSchedule);

}
