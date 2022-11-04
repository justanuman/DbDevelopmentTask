package com.hotel.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class DbUserDto implements Serializable {
    private final String password;
    private final String username;
    private final String firstname;
    private final String lastname;
    private final String addr;
}
