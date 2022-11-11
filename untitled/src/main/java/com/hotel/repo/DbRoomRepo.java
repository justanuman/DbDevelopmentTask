package com.hotel.repo;

import com.hotel.entity.DbRoom;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DbRoomRepo  extends PagingAndSortingRepository<DbRoom,String> {
}
