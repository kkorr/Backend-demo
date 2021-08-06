package com.amr.project.dao.impl;


import com.amr.project.dao.abstracts.AddressDao;
import com.amr.project.model.entity.Address;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AddressDaoImpl extends ReadWriteDAOImpl<Address, Long> implements AddressDao {

}
