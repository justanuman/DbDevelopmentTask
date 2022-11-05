package com.hotel.ver2.service.impl;

import com.hotel.ver2.dto.DbReservationDto;
import com.hotel.ver2.service.interfaces.IReservationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReservationService implements IReservationService {
    @Override
    public DbReservationDto reserveARoom(DbReservationDto dbReservationDto) {
        return null;
    }

    @Override
    public DbReservationDto getReservations(String user) {
        return null;
    }

    @Override
    public DbReservationDto checkReservation(DbReservationDto dbReservationDto) {
        return null;
    }

    @Override
    public DbReservationDto getAllReservation(DbReservationDto dbReservationDto) {
        return null;
    }
}
