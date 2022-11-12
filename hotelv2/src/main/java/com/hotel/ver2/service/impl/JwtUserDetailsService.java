package com.hotel.ver2.service.impl;


import com.hotel.ver2.config.security.JWT.JwtUser;
import com.hotel.ver2.config.security.JWT.JwtUserFactory;
import com.hotel.ver2.entity.DbUser;
import com.hotel.ver2.repo.DbUserRepo;
import com.hotel.ver2.service.interfaces.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class JwtUserDetailsService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(JwtUserDetailsService.class);

   // IUserService userService;IUserService userServicethis.userService = userService;
DbUserRepo dbUserRepo;
    @Autowired
    public JwtUserDetailsService(DbUserRepo d) {
        this.dbUserRepo=d;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        DbUser user = dbUserRepo.findById(username).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException("username: " + username + " not found");
        }

        JwtUser jwtUser = JwtUserFactory.create(user);
        logger.info("user {} loaded", username);
        return jwtUser;
    }
}
