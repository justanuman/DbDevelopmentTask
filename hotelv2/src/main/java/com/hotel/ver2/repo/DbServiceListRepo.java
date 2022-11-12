package com.hotel.ver2.repo;

import com.hotel.ver2.entity.DbServiceList;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface DbServiceListRepo extends PagingAndSortingRepository<DbServiceList,String> {
    @Override
    Optional<DbServiceList> findById(String s);
}
