package com.amr.project.webapp.rest_controller;

import com.amr.project.converter.CategoryMapper;
import com.amr.project.converter.CityMapper;
import com.amr.project.converter.CountryMapper;
import com.amr.project.model.dto.CategoryDto;
import com.amr.project.model.dto.CityDto;
import com.amr.project.model.dto.CountryDto;
import com.amr.project.model.entity.City;
import com.amr.project.model.entity.Country;
import com.amr.project.service.abstracts.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/country")
public class CountryRestController {

    private CountryService countryService;

    @Autowired
    public CountryRestController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryDto> getAddress(@PathVariable("id") Long id) {
        return new ResponseEntity<>(CountryMapper.INSTANCE.countryToDto(countryService.getByKey(id)), HttpStatus.OK);
    }

    @PutMapping("/save")
    public ResponseEntity<Void> saveCountry(@RequestBody CountryDto countryDto) {
        countryDto.setCities(countryService.getByKey(countryDto.getId()).getCities());
        countryService.update(CountryMapper.INSTANCE.dtoToCountry(countryDto));
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<Void> addCountry(@RequestBody CountryDto countryDto) {
        countryService.persist(CountryMapper.INSTANCE.dtoToCountry(countryDto));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        countryService.deleteByKeyCascadeIgnore(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
