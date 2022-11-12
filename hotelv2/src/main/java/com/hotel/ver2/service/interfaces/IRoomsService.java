package com.hotel.ver2.service.interfaces;

import com.hotel.ver2.entity.DbRoom;

import java.util.List;

public interface IRoomsService {





    List<DbRoom> showRooms(int page, String sortingOption);

    //  @PreAuthorize("hasRole(ROLE_ADMIN) or hasRole(ROLE_MODERATOR)")
    DbRoom addRoom(DbRoom room);
    //@PreAuthorize("hasRole(ROLE_ADMIN) or hasRole(ROLE_MODERATOR)")
    String removeRoom(String number);
}
