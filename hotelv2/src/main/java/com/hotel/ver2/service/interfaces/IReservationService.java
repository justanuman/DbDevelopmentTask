package com.hotel.ver2.service.interfaces;

import com.hotel.ver2.dto.DbReservationDto;
import com.hotel.ver2.entity.DbReservation;

import java.util.List;

public interface IReservationService {
    DbReservation reserveARoom(DbReservationDto dbReservationDto);

   // DbReservation deleteARoom(DbReservationDto dbReservationDto);

    void deleteARoom(int id);



    DbReservation updateAReservation(DbReservationDto dbReservationDto, DbReservation dbReservation);

    List<DbReservation> getReservations(String user);

    DbReservation findReservation(int id);

    List<DbReservation> getAllReservation(int page);
}
