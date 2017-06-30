package io.egen.repository;

import io.egen.entity.Alert;
import io.egen.entity.Reading;

import java.util.List;

/**
 * Created by nitir on 6/29/2017.
 */
public interface AlertRepository {
    List<Alert> findAll();

    Alert findOne(String id);

    Alert create(Alert alert);
}
