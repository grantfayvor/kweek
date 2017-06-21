package com.kweek.service;

import com.kweek.model.Coordinates;
import com.kweek.model.Driver;
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

    public Integer updateDriverState(Driver driver){
        return driverRepository.updateDriverState(driver.isReady(), driver.getLat(), driver.getLng(), driver.getId());
    }

    public void alertNearbyDrivers(Coordinates coordinates){
        ArrayList<Driver> drivers = findAvailableDrivers();
        List<String> emailAddresses = drivers.stream().map(driver -> driver.getUser().getEmail()).collect(Collectors.toList());
        Object[] object = emailAddresses.toArray();
        if(object instanceof String[]){
            String[] addresses = (String[]) object;
            String subject = "There is a passenger near you";
            String text = "Follow this link to accept passenger invitation \n Here are the coordinates : " + coordinates.toString() + "" +
                    "\n http://localhost:9080/accept-ride";
            emailService.sendMessageToMultiple(addresses, subject, text);
        }
    }

    public ArrayList<Coordinates> driversLocation(){
        ArrayList<Driver> availableDrivers = findAvailableDrivers();
        return availableDrivers.stream().map(Coordinates::new).collect(Collectors.toCollection(ArrayList::new));
    }

    private ArrayList<Driver> findAvailableDrivers(){
        return driverRepository.findByReadyTrue();
    }
}
