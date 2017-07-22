package io.egen.repository;

import io.egen.entity.Reading;
import io.egen.entity.Tire;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by nitir on 6/29/2017.
 */
@Repository
public class ReadingRepositoryImpl implements ReadingRepository{
    @PersistenceContext
    private EntityManager entityManager;

    public List<Reading> findAll() {
        TypedQuery<Reading> query = entityManager.createNamedQuery("Reading.findAll",Reading.class);
        return query.getResultList();
    }

    public List<Reading> findAllByVin(String vin, Timestamp timeInterval) {
        TypedQuery<Reading> query = entityManager.createNamedQuery("Reading.findByVin",Reading.class);
        query.setParameter("paramVin", vin);
        query.setParameter("time",timeInterval);
        List<Reading> resultList = query.getResultList();
        return query.getResultList();
    }

    public Reading create(Reading reading) {
        entityManager.persist(reading);
        return reading;
    }

    public Tire create(Tire tire) {
        entityManager.persist(tire);
        return tire;
    }
}
