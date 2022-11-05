package com.hotel.ver2.config.security.JWT;


import com.hotel.ver2.entity.DbRole;
import com.hotel.ver2.entity.DbUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(DbUser user) {
        return new JwtUser(
                user.getUsername(),
                user.getPassword(),
                mapToGrantedAuthorities(new ArrayList<>(user.getRoles())),
                user.getStatus().equals("ENABLED"),
                user.getUpdated()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<DbRole> userRoles) {
        List<GrantedAuthority> out = new ArrayList<>();
        for (DbRole role : userRoles) {
            out.add(new SimpleGrantedAuthority(role.getRolename()));
        }
        return out;
    }
}