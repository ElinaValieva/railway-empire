package com.elina.railwayApp.integrations;

import com.elina.railwayApp.DTO.SeatDTO;
import com.elina.railwayApp.DTO.TicketDTO;
import com.elina.railwayApp.configuration.*;
import com.elina.railwayApp.configuration.common.Utils;
import com.elina.railwayApp.exception.BusinessLogicException;
import com.elina.railwayApp.model.Schedule;
import com.elina.railwayApp.model.Seat;
import com.elina.railwayApp.model.User;
import com.elina.railwayApp.service.ScheduleService;
import com.elina.railwayApp.service.SeatService;
import com.elina.railwayApp.service.TicketService;
import com.elina.railwayApp.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        HibernateConfiguration.class,
        SecurityConfiguration.class,
        SecurityInitializer.class,
        ViewConfigurations.class,
        ViewInitializer.class})
@WebAppConfiguration
public class TicketServiceJTest {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private UserService userService;

    @Autowired
    private SeatService seatService;

    private TicketDTO ticketDTO;

    private User userPatric;
    private User userBob;

    @Before
    public void init() {
        userPatric = userService.findUserByEmail("jones@mail.ru");
        userBob = userService.findUserByEmail("micke@mail.ru");
        ticketDTO = new TicketDTO();
        SeatDTO seatDTO = new SeatDTO();
        seatDTO.setCarriage(1);
        seatDTO.setSeat(5);
        ticketDTO.setSchedule(169L);
        ticketDTO.setSeatDTO(seatDTO);
    }

    @Test
    public void getAll() {
        Assert.assertNotNull(ticketService.getAll());
    }

    @Test
    public void getById() {
        Assert.assertNotNull(ticketService.getById(48L));
    }

    @Test
    public void getBookingSeatsBySchedule() {
        Schedule schedule = scheduleService.getById(29L);
        Assert.assertNotNull(ticketService.getBookingSeatsBySchedule(schedule));
    }

    @Test
    public void checkUserUntilBooking() {
        User user = userService.findUserByEmail("bob@mail.ru");
        Schedule schedule = scheduleService.getById(29L);
        Assert.assertFalse(ticketService.checkUserUntilBooking(user, schedule));
    }

    @Test
    public void checkSeatUntilBooking() {
        Seat seat = seatService.getById(3170L);
        Schedule schedule = scheduleService.getById(29L);
        Assert.assertFalse(ticketService.checkSeatUntilBooking(seat, schedule));
    }

    @Test
    public void getBySchedules() {
        Schedule schedule = scheduleService.getById(29L);
        Assert.assertTrue(ticketService.getBySchedules(schedule).size() > 0);
    }

    @Test
    public void getByScheduleId() {
        Assert.assertTrue(ticketService.getByScheduleId(29L).size() > 0);
    }

    @Test
    public void getByDate() throws ParseException {
        Date date = Utils.parseToDate("2018-07-20");
        Assert.assertTrue(ticketService.getByDate(date).size() > 0);
    }

    @Test
    public void getByDates() throws ParseException {
        Date dateFrom = Utils.parseToDate("2018-07-20");
        Date dateTo = Utils.parseToDate("2018-07-30");
        Assert.assertTrue(ticketService.getByDates(dateFrom, dateTo).size() > 0);
    }

    /**
     * concurrency test by booking ticket
     * @throws InterruptedException
     * @throws BrokenBarrierException
     */

    @Test
    public void add() throws InterruptedException, BrokenBarrierException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        try {
            System.out.println("******************************");
            System.out.println(userPatric.getFirstName() + " try to book ticket ...");
            System.out.println("******************************");
            executorService.submit((Runnable) ticketService.add(ticketDTO, userPatric));
        } catch (Exception e) {
            if (e instanceof BusinessLogicException) {
                System.out.println(((BusinessLogicException) e).getError());
                System.out.println("***************Oops..************");
                System.out.println(userPatric.getFirstName() + " didn't booked ticket ...");
                System.out.println("*********************************");
            }
        }
        try {
            System.out.println("******************************");
            System.out.println(userBob.getFirstName() + " try to book ticket ...");
            executorService.submit((Runnable) ticketService.add(ticketDTO, userBob));
            System.out.println("******************************");
        } catch (Exception e) {
            if (e instanceof BusinessLogicException) {
                System.out.println(((BusinessLogicException) e).getError());
                System.out.println("***************Oops..************");
                System.out.println(userBob.getFirstName() + " didn't boob ticket");
                System.out.println("*********************************");
            }
        }
        executorService.awaitTermination(10, TimeUnit.MILLISECONDS);
    }
}
