package io.egen.repository;

import io.egen.entity.Alert;
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
public class AlertRepositoryImpl implements AlertRepository{

    @PersistenceContext
    private EntityManager entityManager;
    public List<Alert> findAll(Timestamp time) {

        TypedQuery<Alert> query = entityManager.createNamedQuery("Alert.findAllByTime",Alert.class);
        query.setParameter("paramInterval", time);
        //query.setParameter("paramNow", now);
        List<Alert> resultList=query.getResultList();
        System.out.println("Size of alerts:"+resultList.size());
        return resultList;
    }

    public List<Alert> findAllByVin(String vin, Timestamp time) {

        TypedQuery<Alert> query = entityManager.createNamedQuery("Alert.findByVin",Alert.class);
        query.setParameter("paramVin", vin);
        query.setParameter("paramInterval",time);
        List<Alert> resultList=query.getResultList();
        System.out.println("Size of alerts:"+resultList.size());
        return resultList;
    }

    public Alert create(Alert alert) {
        entityManager.persist(alert);
        return alert;
    }
}
