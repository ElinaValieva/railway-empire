package com.elina.railwayApp.configuration.common;

public final class URLs {
    public final static String WELCOME = "/home";
    public final static String LOGIN = "/login";
    public final static String REGISTRATION = "/registration";
    public final static String UPDATE_PROFILE = "/home/update";


    /**
     * STATION
     */
    public final static String STATION = "/station";
    public final static String GET_STATIONS = "/station/all";
    public final static String CREATE_STATION = "/station/add";
    public final static String DELETE_STATION = "/station/delete/{id}";
    public final static String UPDATE_STATION = "/station/update";
    public static final String STATION_UPDATE = "/station/update/{id}";

    /**
     * TRAIN
     */
    public final static String TRAIN = "/train";
    public final static String GET_TRAINS = "/train/all";
    public final static String CREATE_TRAIN = "/train/add";
    public final static String DELETE_TRAIN = "/train/remove";

    /**
     * SCHEDULE
     */
    public final static String SCHEDULE = "/schedule";
    public final static String SCHEDULE_CREATE = "/createSchedule";
    public final static String GET_SCHEDULES = "/all";
    public final static String CREATE_SCHEDULE = "/add";
    public final static String DELETE_SCHEDULE = "/remove";
    public final static String GET_SCHEDULE_DIRECT = "/direct";
    public final static String GET_SCHEDULE_DIRECT_BY_STATIONS = "/directByStations";
    public final static String GET_SCHEDULE_DIRECT_BY_TRAIN = "/directByTrain";
    public final static String GET_SCHEDULE_DIRECT_BY_DATES = "/directByDates";
    public final static String GET_SCHEDULE_TRANSFER_BY_STATIONS = "/transferByStations";
    public final static String GET_SEATS_INFO_OF_DIRECT_TRIP = "/seat/{id}";

}

