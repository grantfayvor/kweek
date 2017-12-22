package com.kweek.service;

import com.kweek.model.Ride;
import com.kweek.repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Harrison on 2017-10-17.
 */

@Service
public class RideService implements ServiceInterface<Ride>{

    @Autowired
    private RideRepository repository;

    @Override
    public Ride save(Ride ride) {
        return repository.save(ride);
    }

    @Override
    public Ride find(long id) {
        return repository.findOne(id);
    }

    @Override
    public List<Ride> findAll() {
        return repository.findAll();
    }

    @Override
    public Boolean update(Ride ride) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Boolean delete(Ride ride) {
        return null;
    }

    @Override
    public Page<Ride> findAll(PageRequest pageRequest) {
        return null;
    }

    @Override
    public Page<Ride> findByParam(String param, PageRequest pageRequest) {
        return null;
    }
}
