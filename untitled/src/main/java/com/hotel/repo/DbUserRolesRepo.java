package com.hotel.repo;

import com.hotel.entity.DbUser;
import org.springframework.data.repository.CrudRepository;

public interface DbUserRolesRepo extends CrudRepository<DbUser,String> {
}
