package com.kweek.repository;

import com.kweek.model.Reservation;
import com.kweek.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Harrison on 2017-01-23.
 */

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>{

    List<Reservation> findByUser(User user);
}
