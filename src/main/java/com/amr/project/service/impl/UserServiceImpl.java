package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.AddressDao;
import com.amr.project.dao.abstracts.CityDao;
import com.amr.project.dao.abstracts.CountryDao;
import com.amr.project.dao.abstracts.UserDao;
import com.amr.project.model.dto.AddressDto;
import com.amr.project.model.dto.UserDto;
import com.amr.project.model.entity.Address;
import com.amr.project.model.entity.City;
import com.amr.project.model.entity.Country;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl extends ReadWriteServiceImpl<User, Long> implements UserService {

    private final UserDao userDao;
    private AddressDao addressDao;
    private CountryDao countryDao;
    private CityDao cityDao;

    @Autowired
    public UserServiceImpl(UserDao userDao,
                           AddressDao addressDao,
                           CountryDao countryDao,
                           CityDao cityDao) {
        super(userDao);
        this.userDao = userDao;
        this.addressDao = addressDao;
        this.countryDao = countryDao;
        this.cityDao = cityDao;
    }

    public Optional<User> findByUsername(String username)  {
        return userDao.findByUsername(username);
    };

    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public void updateUserOnUserPage(User user, UserDto userDto) {
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setAge(userDto.getAge());
        user.setBirthday(userDto.getBirthday());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setGender(userDto.getGender());

        Address address =  user.getAddress();
        City city = address.getCity();
        Country country = address.getCountry();
        address.setStreet(userDto.getAddress().getStreet());
        address.setHouse(userDto.getAddress().getHouse());
        address.setCityIndex(userDto.getAddress().getCityIndex());
        city.setName(userDto.getAddress().getCity());
        country.setName(userDto.getAddress().getCountry());
        cityDao.update(city);
        countryDao.update(country);
        addressDao.update(address);
        super.update(user);

    }


}
