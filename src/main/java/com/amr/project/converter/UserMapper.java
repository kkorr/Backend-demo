package com.amr.project.converter;


import com.amr.project.model.dto.AddressDto;
import com.amr.project.model.dto.CountryDto;
import com.amr.project.model.dto.UserDto;
import com.amr.project.model.entity.Address;
import com.amr.project.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    @Mapping(source = "address", target = "address")
    public UserDto userToDto(User user);

    @Mapping(source = "country.name", target = "country")
    @Mapping(source = "city.name", target = "city")
    public AddressDto addressToDto(Address address);

    @Mapping(source = "address", target = "address")
    public User dtoToUser(UserDto userDto);

    @Mapping(source = "country", target = "country.name")
    @Mapping(source = "city", target = "city.name")
    public Address dtoToAddress(AddressDto addressDto);


}
