package com.kweek.repository;

import com.kweek.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Harrison on 2017-01-23.
 */

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>{

    Reservation findByUserId(long id);
}
