package com.amr.project.service.impl;


import com.amr.project.dao.abstracts.CountryDao;
import com.amr.project.dao.abstracts.ReadWriteDAO;
import com.amr.project.model.entity.Country;
import com.amr.project.service.abstracts.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl extends ReadWriteServiceImpl<Country, Long> implements CountryService {

    private final CountryDao countryDao;

    @Autowired
    public CountryServiceImpl(ReadWriteDAO<Country, Long> readWriteDAO, CountryDao countryDao) {
        super(readWriteDAO);
        this.countryDao = countryDao;
    }
}
