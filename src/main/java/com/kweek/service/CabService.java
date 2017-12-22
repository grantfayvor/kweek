package com.kweek.service;

import com.kweek.model.Coordinates;
import com.kweek.model.Driver;
import com.kweek.model.Ride;
import com.kweek.model.User;
import com.kweek.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Harrison on 2017-06-14.
 */

@Service
public class CabService {

    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private EmailServiceImpl emailService;
    @Autowired
    private RideService rideService;

    public Integer updateDriverState(Driver driver) {
        return driverRepository.updateDriverState(driver.isReady(), driver.getLat(), driver.getLng(), driver.getUser());
    }

    public void alertNearbyDrivers(Coordinates coordinates, String destination) {
        ArrayList<Driver> drivers = findAvailableDrivers();
        List<String> emailAddresses = drivers.stream().map(driver -> driver.getUser().getEmail()).collect(Collectors.toList());
        String[] addresses = emailAddresses.toArray(new String[emailAddresses.size()]);
        String subject = "There is a passenger near you";
        String text = "Follow this link to accept passenger invitation \n Here are the passenger's coordinates : " + coordinates.toString() + " and the passenger's destination is "
                +destination +"\n http://localhost:9080/accept-ride?" +coordinates.toString() +"&destination=" +destination;
        emailService.sendMessageToMultiple(addresses, subject, text);
    }

    public ArrayList<Coordinates> driversLocation() {
        ArrayList<Driver> availableDrivers = findAvailableDrivers();
        return availableDrivers.stream().map(Coordinates::new).collect(Collectors.toCollection(ArrayList::new));
    }

    public boolean acceptRide(Driver driver, User passenger){
        Ride ride = new Ride(passenger, driver);
        return rideService.save(ride) != null;
    }

    private ArrayList<Driver> findAvailableDrivers() {
        return driverRepository.findByReadyTrue();
    }
}
