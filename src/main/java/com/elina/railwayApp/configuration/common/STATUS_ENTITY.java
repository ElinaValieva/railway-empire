package com.elina.railwayApp.configuration.common;

import com.elina.railwayApp.model.Status;

public class STATUS_ENTITY {

    public static final String WORKED = "WORKED";
    public static final String NOT_USED = "UN_USED";
    public static final String DELETED = "DELETED";

    public static Status worked(){
        Status status = new Status();
        status.setStatusName(WORKED);
        return status;
    }

    public static Status notUsed(){
        Status status = new Status();
        status.setStatusName(NOT_USED);
        return status;
    }

    public static Status deleted(){
        Status status = new Status();
        status.setStatusName(DELETED);
        return status;
    }
}
