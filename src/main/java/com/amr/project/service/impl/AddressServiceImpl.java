package com.amr.project.service.impl;


import com.amr.project.dao.abstracts.AddressDao;
import com.amr.project.dao.abstracts.ReadWriteDAO;
import com.amr.project.model.entity.Address;
import com.amr.project.service.abstracts.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl extends ReadWriteServiceImpl<Address, Long> implements AddressService {

    private final AddressDao addressDao;

    @Autowired
    public AddressServiceImpl(ReadWriteDAO<Address, Long> readWriteDAO, AddressDao addressDao) {
        super(readWriteDAO);
        this.addressDao = addressDao;
    }
}
