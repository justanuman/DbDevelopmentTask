package com.hotel.ver2.service.interfaces;

import com.hotel.ver2.dto.DbUserDto;
import com.hotel.ver2.entity.DbUser;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.List;

@Component
public interface IUserService {

    DbUser findByUsername(String username);

    DbUser register(DbUserDto userData);

    @PreAuthorize("hasRole(ROLE_USER) and #id == authentication.principal.id")
    DbUserDto updateProfile(Integer id, DbUserDto userProfileDto, DbUser user);
    List<DbUserDto> getAdmins();
    List<DbUserDto> getBills();


    @PreAuthorize("hasRole(ROLE_ADMIN) or hasRole(ROLE_MODERATOR)")
    DbUserDto deactivateUser(int id,  Principal principal);
    @PreAuthorize("hasRole(ROLE_ADMIN) or hasRole(ROLE_MODERATOR)")
    DbUserDto promoteUser(DbUser user);

   /* @PreAuthorize("hasRole(ROLE_ADMIN)")
    UserDto deactivateModerator(int id, DbUser user);

    @PreAuthorize("hasRole(ROLE_ADMIN) or hasRole(ROLE_MODERATOR)")
    UserDto activateUser(DbUser user);



    @PreAuthorize("hasRole(ROLE_ADMIN) or hasRole(ROLE_MODERATOR)")
    UserDto changeUserRating(DbUser user, int score);

    UserDto payForRating(DbUser users, int score);

    void checkForNull(Object object);*/
}