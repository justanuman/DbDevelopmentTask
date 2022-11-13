package com.hotel.ver2.service.interfaces;

import com.hotel.ver2.dto.DbReservationDto;
import com.hotel.ver2.entity.DbReservation;

import java.util.List;

public interface IReservationService {
    DbReservation reserveARoom(DbReservationDto dbReservationDto);
    List<DbReservation> getReservations(String user);

    List<DbReservation> getAllReservation(int page);
}
