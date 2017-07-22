package io.egen.controller;

import io.egen.entity.Vehicle;
import io.egen.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by nitir on 6/28/2017.
 */
@RestController
@RequestMapping(value = "/vehicles")
@CrossOrigin(origins = "*", maxAge = 3600)
public class VehicleController {
    @Autowired
    VehicleService vehicleService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Vehicle> findAll() {
        return vehicleService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{vin}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Vehicle findOne(@PathVariable("vin") String vin) {
        return vehicleService.findOne(vin);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Vehicle> create(@RequestBody List<Vehicle> veh) {
        return vehicleService.create(veh);
    }


}

