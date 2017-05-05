package com.kweek.service;

import com.kweek.config.KweekLoggerFactory;
import com.kweek.model.Reservation;
import com.kweek.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Harrison on 2017-01-24.
 */

@Service
public class ReservationService extends KweekLoggerFactory implements ServiceInterface<Reservation>{

    @Autowired
    private ReservationRepository reservationRepository;

    public Reservation findUserReservation(long id){
        return reservationRepository.findByUserId(id);
    }

    @Override
    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation find(long id) {
        return reservationRepository.findOne(id);
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Boolean update(Reservation reservation) {
        return null;
    }

    @Override
    public Boolean delete(long id) {
        return null;
    }

    @Override
    public Boolean delete(Reservation reservation) {
        return null;
    }

    @Override
    public Page<Reservation> findAll(PageRequest pageRequest) {
        return reservationRepository.findAll(pageRequest);
    }

    @Override
    public Page<Reservation> findByParam(String param, PageRequest pageRequest) {
        return null;
    }
}
