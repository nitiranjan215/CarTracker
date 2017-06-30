package io.egen.repository;

import io.egen.entity.Alert;
import io.egen.entity.Reading;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by nitir on 6/29/2017.
 */
@Repository
public class AlertRepositoryImpl implements AlertRepository{

    @PersistenceContext
    private EntityManager entityManager;
    public List<Alert> findAll() {
        TypedQuery<Alert> query = entityManager.createNamedQuery("Alert.findAll",Alert.class);
        return query.getResultList();
    }

    public Alert findOne(String id) {
        return entityManager.find(Alert.class, id);
            }

    public Alert create(Alert alert) {
        entityManager.persist(alert);
        return alert;
    }
}
