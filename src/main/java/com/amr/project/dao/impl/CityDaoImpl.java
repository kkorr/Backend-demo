package com.amr.project.dao.impl;


import com.amr.project.dao.abstracts.CityDao;
import com.amr.project.model.entity.City;
import org.springframework.stereotype.Repository;

@Repository
public class CityDaoImpl extends ReadWriteDAOImpl<City, Long> implements CityDao {
    public static class ImageDaoImpl {
    }
}
