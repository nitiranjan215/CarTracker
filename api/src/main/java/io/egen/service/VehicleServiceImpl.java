package io.egen.service;

import io.egen.entity.Vehicle;
import io.egen.exception.ResourceNotFoundException;
import io.egen.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nitir on 6/28/2017.
 */
@Service
public class VehicleServiceImpl implements VehicleService{
    @Autowired
    VehicleRepository repository;

    @Transactional(readOnly = true)
    public List<Vehicle> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Vehicle findOne(String vin) {
        Vehicle existing = repository.findOne(vin);
        if (existing == null) {
            throw new ResourceNotFoundException("Vehicle with vin " + vin + " doesn't exist.");
        }
        return existing;
    }
    @Transactional
    public List<Vehicle> create(List<Vehicle> veh) {
        String vin;
        List<Vehicle> resultList=new ArrayList<Vehicle>();
        for(Vehicle vehicle:veh){
            vin=vehicle.getVin();
            Vehicle existing=repository.findOne(vin);
            if (existing!=null){
                resultList.add(repository.update(vehicle));
            }else{
                resultList.add(repository.create(vehicle));
            }

        }
        return resultList;
    }

}
