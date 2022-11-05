package com.hotel.ver2.repo;

import com.hotel.ver2.entity.DbUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DbUserRepo extends JpaRepository< DbUser,String> {
}
