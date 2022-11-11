package com.hotel.repo;

import com.hotel.entity.DbRole;
import org.springframework.data.repository.CrudRepository;

public interface DbRoleRepo extends CrudRepository<DbRole,String> {
}
