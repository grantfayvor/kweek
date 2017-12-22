package com.kweek.repository;

import com.kweek.model.Driver;
import com.kweek.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * Created by Harrison on 2017-06-14.
 */

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Driver d SET d.ready = ?1, d.lat = ?2, d.lng = ?3 WHERE d.user = ?4")
    Integer updateDriverState(boolean ready, double lat, double lng, User user);

    ArrayList<Driver> findByReadyTrue();
}
