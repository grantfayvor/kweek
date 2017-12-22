package com.kweek.service;

import com.kweek.config.KweekLoggerFactory;
import com.kweek.model.Vehicle;
import com.kweek.model.VehicleType;
import com.kweek.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.file.FileSystems;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Harrison on 2017-01-24.
 */

@Service
public class VehicleService extends KweekLoggerFactory implements ServiceInterface<Vehicle>{

    @Autowired
    private VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> findVehicleByBrand(String brand){
        return vehicleRepository.findByBrand(brand);
    }

    public List<Vehicle> findVehicleByType(String type){
        return vehicleRepository.findByType(type);
    }

    // Check this method again
    public List<VehicleType> getAllVehicleTypes(){
        return new ArrayList<>();
    }

    public void saveVehicle(Vehicle vehicle) {
        String separator = FileSystems.getDefault().getSeparator();
        String pathName = "/app/images/KWK" + new BigInteger(130, new SecureRandom()).toString(16)+ ".jpg";
        try {
            FileOutputStream outputStream = new FileOutputStream("src/main/resources/static" +pathName);
            outputStream.write(vehicle.getImage());
            outputStream.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        vehicle.setImageLocation(pathName);
        vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle save(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle find(long id) {
        return vehicleRepository.findOne(id);
    }

    @Override
    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    @Override
    public Boolean update(Vehicle vehicle) {
        return null;
    }

    @Override
    public void delete(long id) {
        vehicleRepository.delete(id);
    }

    @Override
    public Boolean delete(Vehicle vehicle) {
        return null;
    }

    @Override
    public Page<Vehicle> findAll(PageRequest pageRequest) {
        return vehicleRepository.findAll(pageRequest);
    }

    @Override
    public Page<Vehicle> findByParam(String param, PageRequest pageRequest) {
        return null;
    }
}
