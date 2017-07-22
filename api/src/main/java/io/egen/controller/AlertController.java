package io.egen.controller;

import io.egen.entity.Alert;
import io.egen.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by nitir on 7/21/2017.
 */
@RestController
@RequestMapping(value = "/alerts")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AlertController {
    @Autowired
    AlertService alertService;

    @RequestMapping(method = RequestMethod.GET, value = "/time/{time}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Alert> findAll(@PathVariable("time") String time) {
        return alertService.findAll(time);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/vin",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Alert> findAllByVin(@RequestParam("vin") String vin,@RequestParam("time") String time) {
        return alertService.findAllByVin(vin,time);
    }
}
