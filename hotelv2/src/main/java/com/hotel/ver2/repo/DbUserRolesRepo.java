package com.hotel.ver2.repo;

import com.hotel.ver2.entity.DbUser;
import org.springframework.data.repository.CrudRepository;

public interface DbUserRolesRepo extends CrudRepository<DbUser,String> {
}
