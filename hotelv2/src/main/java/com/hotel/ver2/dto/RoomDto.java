package com.hotel.ver2.dto;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
public class RoomDto {

     String id;

     Integer size;

     String rate;

     String info;
}
