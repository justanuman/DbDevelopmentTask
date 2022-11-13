package com.hotel.ver2.repo;

import com.hotel.ver2.entity.DbUser;
import com.hotel.ver2.entity.DbUserRoles;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DbUserRolesRepo extends CrudRepository<DbUserRoles,String> {
    List<DbUserRoles> findAllByUsername(String username);
    List<DbUserRoles> findAllByRolename(String role);
}
