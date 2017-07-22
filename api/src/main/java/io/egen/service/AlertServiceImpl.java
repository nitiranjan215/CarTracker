package io.egen.service;

import io.egen.entity.Alert;
import io.egen.exception.ResourceNotFoundException;
import io.egen.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by nitir on 7/21/2017.
 */
@Service
public class AlertServiceImpl implements AlertService{
    @Autowired
    AlertRepository alertRepository;
    @Transactional(readOnly = true)
    public List<Alert> findAll(String time) {
        Long interval=Long.parseLong(time);
      // Date now = new Date();
      // Date timeInterval=new Date(now.getTime()-interval);
       Timestamp timeInterval = new Timestamp(System.currentTimeMillis()-interval);
       System.out.println("TimeInterval"+timeInterval);
        //return alertRepository.findAll(timeInterval,now);
        return alertRepository.findAll(timeInterval);
    }
    @Transactional(readOnly = true)
    public List<Alert> findAllByVin(String vin, String time) {
        Long interval=Long.parseLong(time);
        // Date now = new Date();
        // Date timeInterval=new Date(now.getTime()-interval);
        Timestamp timeInterval = new Timestamp(System.currentTimeMillis()-interval);

        List<Alert> alerts=alertRepository.findAllByVin(vin,timeInterval);
        if(alerts.size()==0){
            throw new ResourceNotFoundException("Alerts with vin " + vin + " doesn't exist.");
        }
        return alerts;
    }
}
