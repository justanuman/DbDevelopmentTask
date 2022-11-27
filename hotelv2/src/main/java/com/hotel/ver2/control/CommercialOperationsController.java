package com.hotel.ver2.control;

import com.hotel.ver2.dto.DbReservationDto;
import com.hotel.ver2.entity.DbReservation;
import com.hotel.ver2.entity.DbRoom;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommercialOperationsController {

    public String reserveAroom(){
        return null;
    }
    public List<DbReservation> getReservations(String user){
        return null;
    }

    public  List<DbReservation> getAllReservation(int page){
        return null;
    }

    public List<DbRoom> showRooms(int page, String sortingOption){
        return null;
    }

    //  @PreAuthorize("hasRole(ROLE_ADMIN) or hasRole(ROLE_MODERATOR)")
    public DbRoom addRoom(DbRoom room){
        return room;
    }
    //@PreAuthorize("hasRole(ROLE_ADMIN) or hasRole(ROLE_MODERATOR)")
    public String removeRoom(String number){
        return number;
    }
}
