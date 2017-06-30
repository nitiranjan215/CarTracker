package io.egen.service;

import io.egen.entity.Reading;


import java.util.List;

/**
 * Created by nitir on 6/29/2017.
 */
public interface ReadingService {
    List<Reading> findAll();

    Reading findOne(String vin);

    List<Reading> create(Reading veh);
}
