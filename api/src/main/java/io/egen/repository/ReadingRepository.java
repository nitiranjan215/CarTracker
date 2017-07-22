package io.egen.repository;

import io.egen.entity.Reading;
import io.egen.entity.Tire;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by nitir on 6/29/2017.
 */
public interface ReadingRepository {
    List<Reading> findAll();

    List<Reading> findAllByVin(String vin, Timestamp timeInterval);

    Reading create(Reading reading);
    Tire create(Tire tire);
}
