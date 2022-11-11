package com.hotel.repo;

import com.hotel.entity.DbServicedUser;
import org.springframework.data.repository.CrudRepository;

public interface DbServicedUserRepo extends CrudRepository<DbServicedUser,Integer> {
}
