package com.hotel.ver2.repo;

import com.hotel.ver2.entity.DbReservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface DbReservationRepo extends PagingAndSortingRepository<DbReservation, Integer> {
    List<DbReservation> findAllByBookerId(String id);
    @Query("select d from DbReservation d where d.roomNumber = ?1 and d.status = ?2")
    List<DbReservation> findAllByRoomNumberAndStatusEquals(String number, String open);
}
