package com.elina.railwayApp.configuration.common;

public final class URLs {
    /**
     * USERS
     */
    public final static String WELCOME = "/home";
    public final static String LOGIN = "/login";
    public final static String LOGOUT = "/logout";
    public final static String REGISTRATION = "/registration";
    public final static String PROFILE = "/home/profile";
    public final static String UPDATE_PROFILE = "/home/update";
    public final static String GET_PROFILE = "/home/profile/get";
    public final static String TRIPS = "/trips";
    public final static String USERS_MAP = "/userMap";
    public final static String GET_USERS_TICKETS = "/userMap/show";
    public final static String AUDIT = "/audit";
    public final static String DOWNLOAD = "/download/{id}";

    /**
     * CHART
     */
    public final static String CHART = "/chart";
    public final static String GET_POPULAR_STATIONS = "/popularStations/dateFrom/{dateFrom}/dateTo/{dateTo}";
    public final static String GET_STATISTICS_PASSENGERS = "/popularUsersAges";
    public final static String GET_CNT_TICKETS = "/cntTickets";
    public final static String GET_PROFIT_FROM_TICKETS = "/profit/dateFrom/{dateFrom}/dateTo/{dateTo}";


    /**
     * STATION
     */
    public final static String STATION = "/station";
    public final static String GET_STATIONS = "/all";
    public final static String CREATE_STATION = "/add";
    public final static String DELETE_STATION = "/delete/{name}";
    public final static String UPDATE_STATION = "/update";
    public final static String DELETED_STATIONS = "/deletedStations";
    public final static String REESTABLISH_STATION = "/reestablish/{name}";

    /**
     * TRAIN
     */
    public final static String TRAIN = "/train";
    public final static String GET_TRAINS = "/all";
    public final static String GET_ALL_TRAINS = "/allTrains";
    public final static String CREATE_TRAIN = "/add";
    public final static String DELETE_TRAIN = "/delete/{name}";
    public final static String UPDATE_TRAIN = "/update";
    public final static String DELETED_TRAINS = "/deletedTrains";
    public final static String REESTABLISH_TRAIN = "/reestablish/{name}";

    /**
     * SCHEDULE
     */
    public final static String UPDATE_SCHEDULE = "/update";
    public final static String SCHEDULE = "/schedule";
    public final static String SCHEDULE_FOR_REAL_TIME = "/real";
    public final static String GET_SCHEDULES = "/all";
    public final static String CREATE_SCHEDULE = "/add";
    public final static String DELETE_SCHEDULE = "/delete/{id}";
    public final static String GET_SCHEDULE_DIRECT = "/direct";
    public final static String GET_SCHEDULE_DIRECT_BY_STATIONS = "/directByStations";
    public final static String GET_SCHEDULE_DIRECT_BY_TRAIN = "/directByTrain";
    public final static String GET_SCHEDULE_DIRECT_BY_DATES = "/directByDates";
    public final static String GET_SCHEDULE_TRANSFER_BY_STATIONS = "/transferByStations";
    public final static String GET_SEATS_INFO_OF_DIRECT_TRIP = "/seat/{id}";
    public final static String BOOK_TICKET_OF_DIRECT_TRIP = "/ticket";
    public final static String GET_TICKETS = "/ticket/all/{id}";
    public final static String GET_SCHEDULE_TODAY = "/today";
    public final static String GET_SCHEDULES_FOR_BOARD = "/schedule/today";
    public final static String STATIONS_FOR_BOARD = "/station/auto/stations";
    public final static String GET_BY_ID_FOR_BOARD = "/schedule/get/{id}";
    public final static String GET_BY_ID = "/get/{id}";


    /**
     * SEAT
     */
    public final static String SEAT = "/seat";

    /**
     * ITEM RAILWAY
     */
    public final static String ADD_ITEM_RAILWAY = "/addItems";
    public final static String SHOW_ITEMS_RAILWAY = "/showItems";
    public final static String EDIT_ITEMS_RAILWAY = "/editItems";
    public final static String SHOW_DELETED_ITEMS_RAILWAY = "/showDeletedItems";

    /**
     * FOR AUTOCOMPLETE
     */
    public final static String STATIONS_AUTOCOMPLETE = "/auto/stations";
    public final static String TRAINS_AUTOCOMPLETE = "/auto/trains";


}

