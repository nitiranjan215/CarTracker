package io.egen.service;

import io.egen.entity.Alert;

import java.util.List;

/**
 * Created by nitir on 7/21/2017.
 */
public interface AlertService {
    List<Alert> findAll(String time);

    List<Alert> findAllByVin(String vin, String time);


}
