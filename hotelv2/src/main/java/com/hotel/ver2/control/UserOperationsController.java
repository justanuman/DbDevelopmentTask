package com.hotel.ver2.control;

import com.hotel.ver2.config.security.JWT.JwtTokenProvider;
import com.hotel.ver2.dto.DbUserDto;
import com.hotel.ver2.entity.DbUser;
import com.hotel.ver2.entity.DbUserRoles;
import com.hotel.ver2.service.interfaces.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserOperationsController {
    private static final Logger logger = LoggerFactory.getLogger(UserOperationsController.class);

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final IUserService userService;

    @Autowired
    public UserOperationsController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, IUserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }


    @GetMapping(
            value = "/greetings-with-response-body",
            produces = "application/json"
    )
    @ResponseBody
    public String test() {
        return "{\"test\": \"Hello using @ResponseBody\"}";
    }

    @PostMapping(value = "/login",
            produces = "application/json")
    public ResponseEntity login(@RequestParam String username, @RequestParam String password) {
        try {
            //String username = requestDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            //  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("root", "root"));
            DbUser user = userService.findByUsername(username);
            //System.out.println(user.touserDTO().toString());
            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            String token = jwtTokenProvider.createToken(username, user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping(value = "/register",
            produces = "application/json")
    @ResponseBody
    public ResponseEntity register(@RequestParam String username, @RequestParam String password, @RequestParam String email) {
        DbUser newUser = DbUser.builder().username(username).password(password).build();


        //todo input check
       // userService.register(newUser);
        //     try {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        DbUser user = userService.findByUsername(username);
        /*    if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }*/
        String token = jwtTokenProvider.createToken(username, user.getRoles());
        Map<Object, Object> response = new HashMap<>();
        response.put("username", username);
        response.put("token", token);
        return ResponseEntity.ok(response);
       /* } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }*/
    }



/*
    //@PreAuthorize("hasRole(ROLE_USER) and #id == authentication.principal.id")
    // DbUserDto updateProfile(Integer id, DbUserDto userProfileDto, DbUser user);
    @GetMapping
    @ResponseBody
    public List<DbUserRoles> getAdmins(){
        return null;
    }
    @GetMapping
    @ResponseBody
    public List<DbUser> getBills(){
        return null;
    }


    // @PreAuthorize("hasRole(ROLE_ADMIN) or hasRole(ROLE_MODERATOR)")
    // DbUserDto deactivateUser(int id,  Principal principal);
    //  @PreAuthorize("hasRole(ROLE_ADMIN) or hasRole(ROLE_MODERATOR)")
    @PutMapping
    @ResponseBody
    public String promoteUser(String ID){
        return ID;
    }*/
}
