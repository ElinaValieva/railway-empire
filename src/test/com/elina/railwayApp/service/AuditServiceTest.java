package com.elina.railwayApp.service;

import com.elina.railwayApp.DAO.AuditDAO;
import com.elina.railwayApp.model.Audit;
import com.elina.railwayApp.model.User;
import com.elina.railwayApp.service.Implementation.AuditServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuditServiceTest {

    private Audit audit;
    private User user;

    @Mock
    private AuditDAO auditDAO;

    @InjectMocks
    private AuditServiceImpl auditService;

    @Before
    public void init() {
        user = new User();
        user.setId(47L);
        user.setFirstName("Elina");
        user.setLastName("Valieva");
        user.setLogin("veaufa@mail.ru");

        audit = new Audit();
        audit.setDate(new Date());
        audit.setUser(user);
        audit.setOperations("CREATE");
        audit.setNewValue("T145");
    }

    @Test
    public void createTrainAuditInfo() {
    }

    @Test
    public void deleteTrainAuditInfo() {
    }

    @Test
    public void updateTrainAuditInfo() {
    }

    @Test
    public void reestablishTrainAuditInfo() {
    }

    @Test
    public void createStationAuditInfo() {
    }

    @Test
    public void deleteStationAuditInfo() {
    }

    @Test
    public void updateStationAuditInfo() {
    }

    @Test
    public void reestablishStationAuditInfo() {
    }

    @Test
    public void createScheduleAuditInfo() {
    }

    @Test
    public void deleteScheduleAuditInfo() {
    }

    @Test
    public void updateScheduleAuditInfo() {
    }

    @Test
    public void getAuditsInfo() {
        when(auditDAO.getAll()).thenReturn(new ArrayList<>());
        auditService.getAuditsInfo();
        verify(auditDAO).getAll();
    }
}