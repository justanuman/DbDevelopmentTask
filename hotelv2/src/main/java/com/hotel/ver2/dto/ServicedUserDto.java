package com.hotel.ver2.dto;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Basic;
import javax.persistence.Column;
import java.sql.Timestamp;
@Data
public class ServicedUserDto {
    private Integer id;
    private String username;
    private String servicename;
    private String status;
}
