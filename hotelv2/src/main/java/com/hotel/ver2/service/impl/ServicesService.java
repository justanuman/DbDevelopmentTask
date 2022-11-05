package com.hotel.ver2.service.impl;

import com.hotel.ver2.entity.DbServiceList;
import com.hotel.ver2.service.interfaces.IServicesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ServicesService implements IServicesService {
    @Override
    public DbServiceList renderServiceTo(String name) {
        return null;
    }

    @Override
    public DbServiceList showAllServices() {
        return null;
    }

    @Override
    public DbServiceList showAllRenderedServicesTo(String name) {
        return null;
    }
}
