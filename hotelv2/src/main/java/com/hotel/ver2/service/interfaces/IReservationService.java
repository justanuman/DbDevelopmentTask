package com.hotel.ver2.service.interfaces;

import com.hotel.ver2.dto.DbReservationDto;

public interface IReservationService {
    DbReservationDto reserveARoom(DbReservationDto dbReservationDto);
    DbReservationDto getReservations(String user);
    DbReservationDto checkReservation(DbReservationDto dbReservationDto);
    DbReservationDto getAllReservation(DbReservationDto dbReservationDto);
}
