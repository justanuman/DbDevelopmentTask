package com.hotel.ver2.service.impl;

import com.hotel.ver2.entity.DbRoom;
import com.hotel.ver2.service.interfaces.IRoomsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoomsService implements IRoomsService {
    @Override
    public DbRoom showRooms() {
        return null;
    }

    @Override
    public DbRoom addRoom(DbRoom room) {
        return null;
    }

    @Override
    public DbRoom removeRoom(String number) {
        return null;
    }
}
