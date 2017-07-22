package io.egen.controller;

import io.egen.entity.Alert;
import io.egen.entity.Reading;
import io.egen.service.ReadingService;
import io.egen.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by nitir on 6/29/2017.
 */
@RestController
@RequestMapping(value = "/readings")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ReadingController {
    @Autowired
    ReadingService readingService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Reading> findAll() {
        return readingService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{vin}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Reading> findAllByVin(@PathVariable("vin") String vin,@RequestParam("time") String time) {

        return readingService.findAllByVin(vin,time);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Reading> create(@RequestBody Reading reading) {
        return readingService.create(reading);
    }


}
