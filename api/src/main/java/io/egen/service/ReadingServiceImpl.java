package io.egen.service;

import io.egen.entity.Alert;
import io.egen.entity.Reading;
import io.egen.entity.Tire;
import io.egen.entity.Vehicle;
import io.egen.exception.BadRequestException;
import io.egen.exception.ResourceNotFoundException;
import io.egen.repository.AlertRepository;
import io.egen.repository.ReadingRepository;
import io.egen.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nitir on 6/29/2017.
 */
@Service
public class ReadingServiceImpl implements ReadingService{

    @Autowired
    ReadingRepository readingRepository;
    @Autowired
    VehicleRepository vehicleRepository;
    @Autowired
    AlertRepository alertRepository;
    @Transactional(readOnly = true)
    public List<Reading> findAll() {
        return readingRepository.findAll();
    }
    @Transactional(readOnly = true)
    public Reading findOne(String vin) {
        Reading existing=readingRepository.findOne(vin);
        if(existing==null){
            throw new ResourceNotFoundException("Reading with vin " + vin + " doesn't exist.");
        }
        return existing;
    }
    @Transactional
    public List<Reading> create(Reading reading) {
        String vin;
        List<Reading> resultList=new ArrayList<Reading>();
        Tire tire;

            vin=reading.getVin();
            Vehicle existingVehicle=vehicleRepository.findOne(vin);
            System.out.println(existingVehicle);
            if (existingVehicle!=null){
                tire=readingRepository.create(reading.getTires());
                resultList.add(readingRepository.create(reading));

                if(reading.getEngineRpm()>existingVehicle.getRedlineRpm()){
                    Alert alert=new Alert();
                    alert.setPriority("HIGH");
                    alert.setMessage("EngineRpm is greater than the maximun redlineRpm of the vehicle");
                    alert.setVin(vin);
                    alertRepository.create(alert);
                }
                if(reading.getFuelVolume()<(0.1*existingVehicle.getMaxFuelVolume())){
                    Alert alert=new Alert();
                    alert.setPriority("MEDIUM");
                    alert.setMessage("FuelVolume is less than 10% of maximum fuelVolume of the vehicle");
                    alert.setVin(vin);
                    alertRepository.create(alert);
                }
                if(tire.getFrontLeft()<32||tire.getFrontRight()<32||tire.getRearLeft()<32||tire.getRearRight()<32){
                    Alert alert=new Alert();
                    alert.setPriority("LOW");
                    alert.setMessage("Pressure Low in Tires");
                    alert.setVin(vin);
                    alertRepository.create(alert);
                }
                if(tire.getFrontLeft()>36||tire.getFrontRight()>36||tire.getRearLeft()>36||tire.getRearRight()>36){
                    Alert alert=new Alert();
                    alert.setPriority("LOW");
                    alert.setMessage("Pressure High in Tires");
                    alert.setVin(vin);
                    alertRepository.create(alert);
                }
                if(reading.isEngineCoolantLow()==true||reading.isCheckEngineLightOn()==true){
                    Alert alert=new Alert();
                    alert.setPriority("LOW");
                    alert.setMessage("Engine Coolant is low or Engine Lights are on");
                    alert.setVin(vin);
                    alertRepository.create(alert);
                }
            }else{
                throw new BadRequestException("Vehicle Information for Vin: " + reading.getVin() + " does not exists.");
            }

        return resultList;
    }
}
