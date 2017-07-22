package io.egen.repository;

import io.egen.entity.Vehicle;

import java.util.List;

/**
 * Created by nitir on 6/29/2017.
 */
public interface VehicleRepository {

    List<Vehicle> findAll();

    Vehicle findOne(String vin);

    Vehicle create(Vehicle veh);

    Vehicle update(Vehicle veh);
}