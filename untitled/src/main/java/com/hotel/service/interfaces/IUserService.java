package com.hotel.service.interfaces;

import com.hotel.entity.DbUser;
import org.springframework.stereotype.Component;

@Component
public interface IUserService {

    DbUser findByUsername(String username);



  //  @PreAuthorize("hasRole(ROLE_USER) and #id == authentication.principal.id")
 //   UserDto updateProfile(Integer id, UserProfileDto userProfileDto, DbUser user);



   /* @PreAuthorize("hasRole(ROLE_ADMIN) or hasRole(ROLE_MODERATOR)")
    UserDTO deactivateUser(int id,  Principal principal);*/

   /* @PreAuthorize("hasRole(ROLE_ADMIN)")
    UserDto deactivateModerator(int id, DbUser user);

    @PreAuthorize("hasRole(ROLE_ADMIN) or hasRole(ROLE_MODERATOR)")
    UserDto activateUser(DbUser user);

    @PreAuthorize("hasRole(ROLE_ADMIN) or hasRole(ROLE_MODERATOR)")
    UserDto promoteUser(DbUser user);

    @PreAuthorize("hasRole(ROLE_ADMIN) or hasRole(ROLE_MODERATOR)")
    UserDto changeUserRating(DbUser user, int score);

    UserDto payForRating(DbUser users, int score);

    void checkForNull(Object object);*/
}