package com.hotel.repo;

import com.hotel.entity.DbReservation;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DbReservationRepo extends PagingAndSortingRepository<DbReservation, Integer> {
}
