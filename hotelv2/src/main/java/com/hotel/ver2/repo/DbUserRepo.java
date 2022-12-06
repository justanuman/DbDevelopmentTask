package com.hotel.ver2.repo;

import com.hotel.ver2.entity.DbUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.util.Pair;

import java.util.List;

public interface DbUserRepo extends JpaRepository< DbUser,String> {

    @Procedure()
    List<String> getBillsOfUsers();
    List<DbUser> findAllByRolesContainingIgnoreCase(String role);
    List<DbUser> findDbUserByUsername(String username);


}
