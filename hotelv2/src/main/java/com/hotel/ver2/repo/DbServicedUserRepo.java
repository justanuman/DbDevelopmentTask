package com.hotel.ver2.repo;

import com.hotel.ver2.entity.DbServicedUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DbServicedUserRepo extends CrudRepository<DbServicedUser,Integer> {

    List<DbServicedUser> findAllByUsername(String username);
}
