package com.hotel.ver2.repo;

import com.hotel.ver2.entity.DbRoom;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public interface DbRoomRepo  extends PagingAndSortingRepository<DbRoom,String> {
    List<DbRoom> findAllById(String id);
    List<DbRoom> findAllByRateBetween(BigDecimal one,BigDecimal two);
    @Query("select d from DbRoom d where d.size = ?1")
    List<DbRoom> findAllBySize(Integer size);
    @Query(value = "select calculate_room_rate(?1, ?2, ?3)" ,nativeQuery = true)
    BigDecimal rateCalc(BigDecimal rate, Timestamp one, Timestamp two);


}
