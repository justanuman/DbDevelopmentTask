package com.hotel.ver2.repo;

import com.hotel.ver2.entity.DbServiceList;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DbServiceListRepo extends PagingAndSortingRepository<DbServiceList,String> {
}
