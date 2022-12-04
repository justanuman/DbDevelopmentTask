package com.hotel.ver2.service.impl;

import com.hotel.ver2.entity.DbRoom;
import com.hotel.ver2.repo.DbReservationRepo;
import com.hotel.ver2.repo.DbRoomRepo;
import com.hotel.ver2.service.interfaces.IRoomsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoomsService implements IRoomsService {
    final
    DbRoomRepo roomDAO;

    final
    DbReservationRepo reservationDAO;
    @Autowired
    public RoomsService(DbRoomRepo roomDAO, DbReservationRepo reservationDAO) {
        this.roomDAO = roomDAO;
        this.reservationDAO = reservationDAO;
    }

    @Override
    public List<DbRoom> showRooms(int page, String sortingOption) {
        List<DbRoom> out;
        Pageable pageRequest;
         if ( "byRateASC".equals(sortingOption)) {
            pageRequest = PageRequest.of(page, 60, Sort.by("rate").ascending());
            out= roomDAO.findAll(pageRequest).toList();
            return out;
        }
        else if ( "byRateDESC".equals(sortingOption)) {
            pageRequest = PageRequest.of(page, 60, Sort.by("rate").descending());
            out= roomDAO.findAll(pageRequest).toList();
            return out;
        }
        else if ( "bySizeDESC".equals(sortingOption)) {
            pageRequest = PageRequest.of(page, 60, Sort.by("size").descending());
            out= roomDAO.findAll(pageRequest).toList();
            return out;
        }
        else if ( "bySizeASC".equals(sortingOption)) {
            pageRequest = PageRequest.of(page, 60, Sort.by("size").ascending());
            out= roomDAO.findAll(pageRequest).toList();
            return out;
        }else  {
            pageRequest = PageRequest.of(page, 20);
            out= roomDAO.findAll(pageRequest).toList();
             return out;
        }
    }

    @Override
    public DbRoom addRoom(DbRoom room) {
        return roomDAO.save(room);
    }

    @Override
    public String removeRoom(String number) {
        DbRoom room = roomDAO.findById(number).orElse(null);
        if(room!=null && (reservationDAO.findAllByRoomNumberAndStatusEquals(number,"OPEN").size())==0){
            roomDAO.delete(room);
            return "удалено";
        }else{
            return "нет такой комнаты";
        }
    }
}
