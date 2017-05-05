package com.kweek.repository;

import com.kweek.model.Vehicle;
import com.kweek.model.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Harrison on 2017-01-23.
 */

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long>{

    List<Vehicle> findByBrand(String brand);

    List<Vehicle> findByType(String type);

}
