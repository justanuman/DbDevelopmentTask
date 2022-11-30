package com.hotel.ver2.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class DbReservationDto implements Serializable {
    private  Integer id;
    private  String bookerID;
    private  String arrival;
    private  String depart;
    private  String roomNumber;
    private  Integer numberOfOccupants;


    public DbReservationDto() {

    }
}
