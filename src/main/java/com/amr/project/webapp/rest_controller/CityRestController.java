package com.amr.project.webapp.rest_controller;


import com.amr.project.converter.CityMapper;
import com.amr.project.converter.CountryMapper;
import com.amr.project.model.dto.CityDto;
import com.amr.project.model.dto.CountryDto;
import com.amr.project.model.entity.City;
import com.amr.project.service.abstracts.AddressService;
import com.amr.project.service.abstracts.CityService;
import com.amr.project.service.abstracts.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/city")
public class CityRestController {

    private CityService cityService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private CountryService countryService;


    @Autowired
    public CityRestController(CityService cityService) {
        this.cityService = cityService;

    }

    @GetMapping("/{id}")
    public ResponseEntity<CityDto> getAddress(@PathVariable("id") Long id) {
        return new ResponseEntity<>(CityMapper.INSTANCE.cityToDto(cityService.getByKey(id)), HttpStatus.OK);
    }

    @PutMapping("/save")
    public ResponseEntity<Long> saveCity(@RequestBody CityDto cityDto) {
        City city = CityMapper.INSTANCE.dtoToCity(cityDto);
        city.setCountry(countryService.getByName(cityDto.getCountry()));
        cityService.update(city);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<Long> addCity(@RequestBody CityDto cityDto) {
        City city = CityMapper.INSTANCE.dtoToCity(cityDto);
        city.setCountry(countryService.getByName(cityDto.getCountry()));

        cityService.persist(city);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CityDto> delete(@PathVariable("id") Long id) {

        cityService
                .getByKey(id)
                .getAddresses()
                .stream()
                .forEach(x -> {
                    x.setCity(null);
                    addressService.update(x);
                });
        cityService.deleteByKeyCascadeEnable(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
