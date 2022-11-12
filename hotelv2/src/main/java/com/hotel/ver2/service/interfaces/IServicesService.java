package com.hotel.ver2.service.interfaces;

import com.hotel.ver2.entity.DbServiceList;
import com.hotel.ver2.entity.DbServicedUser;

import java.util.List;

public interface IServicesService {


    DbServicedUser renderServiceTo(String name, String service);

    List<DbServiceList> showAllServices();
    List<DbServicedUser> showAllRenderedServicesTo(String name);
}
