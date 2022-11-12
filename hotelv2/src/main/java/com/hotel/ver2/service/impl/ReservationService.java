package com.hotel.ver2.service.impl;

import com.hotel.ver2.dto.DbReservationDto;
import com.hotel.ver2.entity.DbReservation;
import com.hotel.ver2.repo.DbReservationRepo;
import com.hotel.ver2.service.interfaces.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReservationService implements IReservationService {
    final DbReservationRepo dbReservationDAO;
@Autowired
    public ReservationService(DbReservationRepo dbReservationDAO) {
        this.dbReservationDAO = dbReservationDAO;
    }

    @Override
    public DbReservation reserveARoom(DbReservationDto dbReservationDto) {
        List<DbReservation> activeReservationsInRoom = dbReservationDAO.findAllByRoomNumberAndDepartAfterAndStatusEquals(dbReservationDto.getRoomNumber(),dbReservationDto.getArrival(),"OPEN");
        if(activeReservationsInRoom.isEmpty()){
            DbReservation dbReservation = DbReservation.builder()
                    .arrival(dbReservationDto.getArrival())
                    .depart(dbReservationDto.getDepart())
                    .numberOfOccupants(dbReservationDto.getNumberOfOccupants())
                    .bookerId(dbReservationDto.getBookerID())
                    .roomNumber(dbReservationDto.getRoomNumber()).build();
            return dbReservationDAO.save(dbReservation);
        }else {  return null;}

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
