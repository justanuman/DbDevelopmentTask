package com.hotel.repo;

import com.hotel.entity.DbServiceList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DbServiceListRepo extends PagingAndSortingRepository<DbServiceList,String> {
}
