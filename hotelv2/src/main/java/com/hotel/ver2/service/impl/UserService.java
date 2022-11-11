package com.hotel.ver2.service.impl;

import com.hotel.ver2.dto.DbUserDto;
import com.hotel.ver2.entity.DbUser;
import com.hotel.ver2.repo.DbRoleRepo;
import com.hotel.ver2.repo.DbUserRepo;
import com.hotel.ver2.repo.DbUserRolesRepo;
import com.hotel.ver2.service.interfaces.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

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

    @Override
    public DbUser register(DbUserDto userData) {
        return null;
    }

    @Override
    public DbUserDto updateProfile(Integer id, DbUserDto userProfileDto, DbUser user) {
        return null;
    }

    @Override
    public List<DbUserDto> getAdmins() {
        return null;
    }

    @Override
    public List<DbUserDto> getBills() {
        return null;
    }

    @Override
    public DbUserDto deactivateUser(int id, Principal principal) {
        return null;
    }

    @Override
    public DbUserDto promoteUser(DbUser user) {
        return null;
    }


}