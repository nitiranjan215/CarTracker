package io.egen.repository;

import io.egen.entity.Vehicle;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by nitir on 6/29/2017.
 */
@Repository
public class VehicleRepositoryImpl implements VehicleRepository {

    @PersistenceContext
    private EntityManager entityManager;


    public List<Vehicle> findAll() {
        TypedQuery<Vehicle> query = entityManager.createNamedQuery("Vehicle.findAll",Vehicle.class);
        return query.getResultList();
    }

    public Vehicle findOne(String vin) {
        TypedQuery<Vehicle> query = entityManager.createNamedQuery("Vehicle.findByVin",
                Vehicle.class);
        query.setParameter("paramVin", vin);
        List<Vehicle> resultList = query.getResultList();
        if (resultList != null && resultList.size() == 1) {
            return resultList.get(0);
        } else {
            return null;
        }
    }

    public Vehicle create(Vehicle veh) {
        entityManager.persist(veh);
        return veh;
    }

    public Vehicle update(Vehicle veh) {
        return entityManager.merge(veh);
    }
}
