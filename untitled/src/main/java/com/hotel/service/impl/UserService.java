package com.hotel.service.impl;

import com.hotel.entity.DbUser;
import com.hotel.repo.DbRoleRepo;
import com.hotel.repo.DbUserRepo;
import com.hotel.repo.DbUserRolesRepo;
import com.hotel.service.interfaces.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService implements IUserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    DbUserRepo userDAO;
    DbRoleRepo roleDAO;
    BCryptPasswordEncoder passwordEncoder;
    DbUserRolesRepo userRoleDAO;
    @Autowired
    public UserService(DbUserRepo userDAO, DbRoleRepo roleDAO, BCryptPasswordEncoder passwordEncoder,
                       DbUserRolesRepo userRoleDAO
    ) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
        this.passwordEncoder = passwordEncoder;
        this.userRoleDAO = userRoleDAO;

    }



    @Override
    public DbUser findByUsername(String username) {
        DbUser result = userDAO.findById(username).orElse(null);
        logger.info("findByUsername {}", username);
        return result;
    }


}