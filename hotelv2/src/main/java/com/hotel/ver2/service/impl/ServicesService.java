package com.hotel.ver2.service.impl;

import com.hotel.ver2.entity.DbServiceList;
import com.hotel.ver2.entity.DbServicedUser;
import com.hotel.ver2.repo.DbServiceListRepo;
import com.hotel.ver2.repo.DbServicedUserRepo;
import com.hotel.ver2.service.interfaces.IServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class ServicesService implements IServicesService {
    final DbServiceListRepo serviceListDAO;
    final DbServicedUserRepo servicedUserDAO;

    @Autowired
    public ServicesService(DbServiceListRepo serviceListDAO, DbServicedUserRepo servicedUserDAO) {
        this.serviceListDAO = serviceListDAO;
        this.servicedUserDAO = servicedUserDAO;
    }

    @Override
    public DbServicedUser renderServiceTo(String name, String serviceName) {
        DbServicedUser dbServicedUser = new DbServicedUser();

        dbServicedUser.setUsername(name);
        dbServicedUser.setServicename(serviceName);
        dbServicedUser.setStatus("OPEN");
        return servicedUserDAO.save(dbServicedUser);
    }

    @Override
    public List<DbServiceList> showAllServices() {
        return StreamSupport.stream(serviceListDAO.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public List<DbServicedUser> showAllRenderedServicesTo(String name) {
        return servicedUserDAO.findAllByUsername(name);
    }
}
