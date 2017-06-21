package com.kweek.controller;

import com.kweek.model.AccountType;
import com.kweek.model.Coordinates;
import com.kweek.model.Driver;
import com.kweek.model.User;
import com.kweek.service.CabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by Harrison on 2017-06-14.
 */

@RestController
@RequestMapping("/api/cab")
public class CabController {

    @Autowired
    private CabService cabService;

    public CabController() {
    }

    @RequestMapping("/driver")
    public Boolean driverIsReady(@RequestBody Coordinates coordinates, @RequestParam("ready") boolean ready, HttpSession httpSession){
        Object object = httpSession.getAttribute("sessionUser");
        if(object instanceof User){
            if(AccountType.ROLE_DRIVER.equals(((User) object).getAccountType())) {
                Driver driver = new Driver((User) object, ready, coordinates);
                return cabService.updateDriverState(driver) > 0;
            } else {
                return false;
            }
        }
        else return false;
    }

    @RequestMapping(value = "/call-a-cab", method = RequestMethod.POST)
    public void callACab(@RequestBody Coordinates coordinates){
        cabService.alertNearbyDrivers(coordinates);
    }

    @RequestMapping("/available-drivers")
    public ArrayList<Coordinates> availableDrivers(){
        return cabService.driversLocation();
    }
}
