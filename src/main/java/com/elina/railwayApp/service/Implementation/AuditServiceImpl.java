package com.elina.railwayApp.service.Implementation;

import com.elina.railwayApp.DAO.AuditDAO;
import com.elina.railwayApp.DTO.AuditDTO;
import com.elina.railwayApp.model.*;
import com.elina.railwayApp.service.AuditService;
import com.elina.railwayApp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuditServiceImpl implements AuditService {

    @Autowired
    private AuditDAO auditDAO;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public void createTrainAuditInfo(Train train) {
        Audit audit = new Audit();
        audit.setDate(new Date());
        audit.setUser(getAuthorisedUser());
        audit.setOperations("CREATE TRAIN");
        audit.setNewValue(train.getName());
        auditDAO.add(audit);
    }

    @Override
    @Transactional
    public void deleteTrainAuditInfo(Train train) {
        Audit audit = new Audit();
        audit.setDate(new Date());
        audit.setUser(getAuthorisedUser());
        audit.setOperations("DELETE TRAIN");
        audit.setNewValue(train.getName());
        auditDAO.add(audit);
    }

    @Override
    @Transactional
    public void updateTrainAuditInfo(String oldValue, String newValue) {
        Audit audit = new Audit();
        audit.setDate(new Date());
        audit.setUser(getAuthorisedUser());
        audit.setOperations("UPDATE TRAIN");
        audit.setOldValue(oldValue);
        audit.setNewValue(newValue);
        auditDAO.add(audit);
    }

    @Override
    @Transactional
    public void reestablishTrainAuditInfo(Train train) {
        Audit audit = new Audit();
        audit.setDate(new Date());
        audit.setUser(getAuthorisedUser());
        audit.setOperations("REESTABLISH TRAIN");
        audit.setNewValue(train.getName());
        auditDAO.add(audit);
    }

    @Override
    @Transactional
    public void createStationAuditInfo(Station station) {
        Audit audit = new Audit();
        audit.setDate(new Date());
        audit.setUser(getAuthorisedUser());
        audit.setOperations("CREATE STATION");
        audit.setNewValue(station.getName());
        auditDAO.add(audit);
    }

    @Override
    @Transactional
    public void deleteStationAuditInfo(Station station) {
        Audit audit = new Audit();
        audit.setDate(new Date());
        audit.setUser(getAuthorisedUser());
        audit.setOperations("DELETE STATION");
        audit.setNewValue(station.getName());
        auditDAO.add(audit);
    }

    @Override
    @Transactional
    public void updateStationAuditInfo(String oldValue, String newValue) {
        Audit audit = new Audit();
        audit.setDate(new Date());
        audit.setUser(getAuthorisedUser());
        audit.setOperations("UPDATE STATION");
        audit.setOldValue(oldValue);
        audit.setNewValue(newValue);
        auditDAO.add(audit);
    }

    @Override
    @Transactional
    public void reestablishStationAuditInfo(Station station) {
        Audit audit = new Audit();
        audit.setDate(new Date());
        audit.setUser(getAuthorisedUser());
        audit.setOperations("REESTABLISH STATION");
        audit.setNewValue(station.getName());
        auditDAO.add(audit);
    }

    @Override
    @Transactional
    public void createScheduleAuditInfo(Schedule schedule) {
        Audit audit = new Audit();
        audit.setDate(new Date());
        audit.setUser(getAuthorisedUser());
        audit.setOperations("CREATE SCHEDULE");
        audit.setNewValue(schedule.getStationDeparture().getName() + " -> "
                + schedule.getStationArrival().getName() + " dates: "
                + schedule.getDateDeparture() + " - "
                + schedule.getDateArrival());
        auditDAO.add(audit);
    }

    @Override
    @Transactional
    public void deleteScheduleAuditInfo(Schedule schedule) {
        Audit audit = new Audit();
        audit.setDate(new Date());
        audit.setUser(getAuthorisedUser());
        audit.setOperations("DELETE SCHEDULE");
        audit.setNewValue(schedule.getStationDeparture().getName() + " -> "
                + schedule.getStationArrival().getName() + " dates: "
                + schedule.getDateDeparture() + " - "
                + schedule.getDateArrival());
        auditDAO.add(audit);
    }

    @Override
    @Transactional
    public void updateScheduleAuditInfo(Schedule oldSchedule, Schedule newSchedule) {
        Audit audit = new Audit();
        audit.setDate(new Date());
        audit.setUser(getAuthorisedUser());
        audit.setNewValue(oldSchedule.getStationDeparture().getName() + " -> "
                + oldSchedule.getStationArrival().getName() + " dates: "
                + oldSchedule.getDateDeparture() + " - "
                + oldSchedule.getDateArrival());
        audit.setOperations("UPDATE SCHEDULE");
        audit.setNewValue(newSchedule.getStationDeparture().getName() + " -> "
                + newSchedule.getStationArrival().getName() + " dates: "
                + newSchedule.getDateDeparture() + " - "
                + newSchedule.getDateArrival());
        auditDAO.add(audit);
    }

    @Override
    @Transactional
    public List<AuditDTO> getAuditsInfo() {
        return auditDAO.getAll().stream()
                .map(x -> modelMapper.map(x, AuditDTO.class))
                .collect(Collectors.toList());
    }

    public User getAuthorisedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByEmail(userName);
        return user;
    }
}
