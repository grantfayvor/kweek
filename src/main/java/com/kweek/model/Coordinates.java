package com.kweek.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Harrison on 2017-06-14.
 */

public class Coordinates {

    private double lat;
    private double lng;

    public Coordinates(){

    }

    public Coordinates(Driver driver) {
        this.lat = driver.getLat();
        this.lng = driver.getLng();
    }

    /**
     * Getter for property 'lat'.
     *
     * @return Value for property 'lat'.
     */
    public double getLat() {
        return lat;
    }

    /**
     * Setter for property 'lat'.
     *
     * @param lat Value to set for property 'lat'.
     */
    public void setLat(double lat) {
        this.lat = lat;
    }

    /**
     * Getter for property 'lng'.
     *
     * @return Value for property 'lng'.
     */
    public double getLng() {
        return lng;
    }

    /**
     * Setter for property 'lng'.
     *
     * @param lng Value to set for property 'lng'.
     */
    public void setLng(double lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "latitude=" + lat +
                "&longitude=" + lng +" ";
    }
}
