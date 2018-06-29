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
    public final static String GET_SCHEDULES = "/schedule/all";
    public final static String CREATE_SCHEDULE = "/schedule/add";
    public final static String DELETE_SCHEDULE = "/schedule/remove";
    public final static String GET_SCHEDULE_BY_DATE_ARRIVAL = "/schedule/getByDate";
    public final static String GET_SCHEDULE_DIRECT = "/schedule/direct";
    public final static String GET_SCHEDULE_TRANSFER = "/schedule/transfer";

}

