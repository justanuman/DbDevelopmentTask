package com.hotel.ver2.service.interfaces;

import com.hotel.ver2.dto.DbReservationDto;
import com.hotel.ver2.entity.DbReservation;

public interface IReservationService {
    DbReservation reserveARoom(DbReservationDto dbReservationDto);
    DbReservationDto getReservations(String user);
    DbReservationDto checkReservation(DbReservationDto dbReservationDto);
    DbReservationDto getAllReservation(DbReservationDto dbReservationDto);
}
