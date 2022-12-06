package com.hotel.ver2.service.impl;

import com.hotel.ver2.dto.DbReservationDto;
import com.hotel.ver2.entity.DbReservation;
import com.hotel.ver2.repo.DbReservationRepo;
import com.hotel.ver2.service.interfaces.IReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
@Transactional
@Slf4j
public class ReservationService implements IReservationService {
    final DbReservationRepo dbReservationDAO;
@Autowired
    public ReservationService(DbReservationRepo dbReservationDAO) {
        this.dbReservationDAO = dbReservationDAO;
    }

    @Override
    public DbReservation reserveARoom(DbReservationDto dbReservationDto) {
        List<DbReservation> activeReservationsInRoom = dbReservationDAO.findAllByRoomNumberAndDepartBeforeAndStatusEquals(dbReservationDto.getRoomNumber(), Timestamp.valueOf(dbReservationDto.getArrival()),"OPEN");
       if(activeReservationsInRoom.isEmpty()){
        DbReservation dbReservation = DbReservation.builder()
                    .arrival(Timestamp.valueOf(dbReservationDto.getArrival()))
                    .depart(Timestamp.valueOf(dbReservationDto.getDepart()))
                    .numberOfOccupants(dbReservationDto.getNumberOfOccupants())
                    .bookerId(dbReservationDto.getBookerID())
                    .roomNumber(dbReservationDto.getRoomNumber())
                .status("OPEN")
                .build();
        return dbReservationDAO.save(dbReservation);
        }else {  return null;}

    }
    @Override
    public void deleteARoom(int id) {
        // List<DbReservation> activeReservationsInRoom = dbReservationDAO.findAllByRoomNumberAndDepartBeforeAndStatusEquals(dbReservationDto.getRoomNumber(),dbReservationDto.getArrival(),"OPEN");
        // if(activeReservationsInRoom.isEmpty())
        dbReservationDAO.delete(dbReservationDAO.findById(id).orElse(null));
        // }else {  return null;}

    }

    @Override
    public DbReservation updateAReservation(DbReservationDto dbReservationDto,DbReservation dbReservation) {
        log.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        //List<DbReservation> activeReservationsInRoom = dbReservationDAO.findAllByRoomNumberAndDepartBeforeAndStatusEquals(dbReservationDto.getRoomNumber(),dbReservationDto.getArrival(),"OPEN");
       // if(activeReservationsInRoom.isEmpty()){
        log.info(dbReservation.getBookerId());
       // dbReservation.setBookerId("3");
        if(Timestamp.valueOf(dbReservationDto.getArrival())!=null){dbReservation.setArrival(Timestamp.valueOf(dbReservationDto.getArrival()));}
        if(Timestamp.valueOf(dbReservationDto.getDepart())!=null){dbReservation.setDepart(Timestamp.valueOf(dbReservationDto.getDepart()));}
        if(dbReservationDto.getNumberOfOccupants()!=null){dbReservation.setNumberOfOccupants(dbReservationDto.getNumberOfOccupants());}
        dbReservationDAO.save(dbReservation);
        return  dbReservation;
        //}else {  return null;}

    }

    @Override
    public List<DbReservation> getReservations(String user) {
        List<DbReservation> activeReservationsInRoom = dbReservationDAO.findAllByBookerId(user);
        return activeReservationsInRoom;

    }

    @Override
    public DbReservation findReservation(int id) {
        return dbReservationDAO.findById(id).orElse(null);

    }

@Override
    public List<DbReservation> getAllReservation(int page) {
        Pageable pageRequest =  PageRequest.of(page, 20);
        List<DbReservation> activeReservationsInRoom = dbReservationDAO.findAll(pageRequest).toList();
        return activeReservationsInRoom;
    }
}
