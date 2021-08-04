package com.amr.project.webapp.rest_controller;

import com.amr.project.converter.CityMapper;
import com.amr.project.converter.CountryMapper;
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

    @DeleteMapping("/{id}")
    public ResponseEntity<CountryDto> delete(@PathVariable("id") Long id) {
        countryService.deleteByKeyCascadeIgnore(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
