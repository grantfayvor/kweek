package com.kweek.controller;

import com.kweek.model.AccountType;
import com.kweek.model.User;
import com.kweek.model.Vehicle;
import com.kweek.model.VehicleType;
import com.kweek.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by Harrison on 2017-01-24.
 */

@Controller
@RequestMapping("/api/vehicle")
public class VehicleController extends AbstractController<Vehicle>{

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    public VehicleController (VehicleService vehicleService){
        super(vehicleService);
    }

    @RequestMapping(value = "/brand/{brandName}", method = RequestMethod.GET)
    @ResponseBody
    public List<Vehicle> findVehicleByBrand(@PathVariable("brandName") String brand){
        return vehicleService.findVehicleByBrand(brand);
    }

    @RequestMapping(value = "/type/{vehicleType}", method = RequestMethod.GET)
    @ResponseBody
    public List<Vehicle> findVehicleByType(@PathVariable("vehicleType") String type){
        return vehicleService.findVehicleByType(type);
    }

    @RequestMapping(value = "/all_types", method = RequestMethod.GET)
    @ResponseBody
    public List<VehicleType> getAllVehicleType(){
        return vehicleService.getAllVehicleTypes();
    }

    @RequestMapping(value = "/new-vehicle", method = RequestMethod.POST)
    @ResponseBody
    public void create(@RequestParam("image")MultipartFile file, @RequestParam("brand") String brand,
                       @RequestParam("type") String type, @RequestParam("model") String model, @RequestParam("cost") String cost,
                       @RequestParam("description") String description, HttpSession httpSession) throws IOException {
        Object object = httpSession.getAttribute("sessionUser");
        if(object instanceof User){
            if (AccountType.ROLE_ADMIN.equals(((User) object).getAccountType())){
                Vehicle vehicle = new Vehicle();
                vehicle.setBrand(brand);
                vehicle.setType(VehicleType.valueOf(type));
                vehicle.setModel(model);
                vehicle.setCost(cost);
                vehicle.setDescription(description);
                vehicle.setImage(file.getBytes());
                vehicleService.saveVehicle(vehicle);
            } else System.out.println("not authorised to perform this function");
        }

    }
}
