package com.hotel.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class DbReservationDto implements Serializable {
    private final Integer id;
    private final Timestamp arrival;
    private final Timestamp depart;
    private final String roomNumber;
    private final Integer numberOfOccupants;
}
