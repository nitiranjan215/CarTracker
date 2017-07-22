package io.egen.repository;

import io.egen.entity.Alert;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by nitir on 6/29/2017.
 */
public interface AlertRepository {
    List<Alert> findAll(Timestamp interval);

    List<Alert> findAllByVin(String vin, Timestamp time);

    Alert create(Alert alert);
}
