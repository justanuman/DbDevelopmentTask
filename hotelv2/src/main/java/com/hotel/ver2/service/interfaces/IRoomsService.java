package com.hotel.ver2.service.interfaces;

import com.hotel.ver2.entity.DbRoom;
import org.springframework.security.access.prepost.PreAuthorize;

public interface IRoomsService {
    @PreAuthorize("hasRole(ROLE_ADMIN) or hasRole(ROLE_MODERATOR)")
    DbRoom showRooms();
    @PreAuthorize("hasRole(ROLE_ADMIN) or hasRole(ROLE_MODERATOR)")
    DbRoom addRoom(DbRoom room);
    @PreAuthorize("hasRole(ROLE_ADMIN) or hasRole(ROLE_MODERATOR)")
    DbRoom removeRoom(String number);
}
