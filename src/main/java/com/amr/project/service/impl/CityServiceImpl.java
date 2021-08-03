package com.amr.project.service.impl;


import com.amr.project.dao.abstracts.CityDao;
import com.amr.project.dao.abstracts.ReadWriteDAO;
import com.amr.project.model.entity.City;
import com.amr.project.service.abstracts.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl extends ReadWriteServiceImpl<City, Long> implements CityService {

    private final CityDao cityDao;

    @Autowired
    public CityServiceImpl(ReadWriteDAO<City, Long> readWriteDAO, CityDao cityDao) {
        super(readWriteDAO);
        this.cityDao = cityDao;
    }

}
