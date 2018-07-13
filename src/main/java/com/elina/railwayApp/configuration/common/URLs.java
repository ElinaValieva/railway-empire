package com.elina.railwayApp.configuration.common;

public final class URLs {
    public final static String WELCOME = "/home";
    public final static String LOGIN = "/login";
    public final static String REGISTRATION = "/registration";
    public final static String UPDATE_PROFILE = "/home/update";
    public final static String USERS_MAP = "/userMap";
    public final static String SHOW_MARKERS_ON_USERS_MAP = "/userMap/show";

    /**
     * CHART
     */
    public final static String CHART = "/chart";
    public final static String GET_POPULAR_STATIONS = "/popularStations/dateFrom/{dateFrom}/dateTo/{dateTo}";
    public final static String GET_STATISTICS_PASSENGERS = "/popularUsersAges";
    public final static String GET_CNT_TICKETS = "/cntTickets/{date}";
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
    public final static String SCHEDULE = "/schedule";
    public final static String UPDATE_SCHEDULE = "/update";
    public final static String NEW_SCHEDULE = "/test";
    public final static String SCHEDULE_CREATE = "/createSchedule";
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
}

