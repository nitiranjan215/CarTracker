package io.egen.service;

import io.egen.entity.Vehicle;

import java.util.List;

/**
 * Created by nitir on 6/28/2017.
 */
public interface VehicleService {
    List<Vehicle> findAll();

    Vehicle findOne(String vin);

    List<Vehicle> create(List<Vehicle> veh);


}
