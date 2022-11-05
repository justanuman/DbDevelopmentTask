package com.hotel.ver2.service.interfaces;

import com.hotel.ver2.entity.DbServiceList;

public interface IServicesService {
    DbServiceList renderServiceTo(String name);
    DbServiceList showAllServices();
    DbServiceList showAllRenderedServicesTo(String name);
}
