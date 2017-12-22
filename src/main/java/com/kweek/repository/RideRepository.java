package com.kweek.repository;

import com.kweek.model.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Harrison on 2017-10-17.
 */

@Repository
public interface RideRepository extends JpaRepository<Ride, Long>{
}
