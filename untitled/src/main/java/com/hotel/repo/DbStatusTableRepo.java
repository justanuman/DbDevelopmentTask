package com.hotel.repo;

import com.hotel.entity.DbStatusTable;
import org.springframework.data.repository.CrudRepository;

public interface DbStatusTableRepo extends CrudRepository< DbStatusTable,String> {
}
