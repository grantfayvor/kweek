package com.kweek.model;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by Harrison on 2017-01-21.
 */

@Entity
@Table
public class Vehicle implements Serializable{

    @Id
    @GeneratedValue
    private long id;
    @Column
    private String brand;
    @Column
    @Enumerated(EnumType.STRING)
    private VehicleType type;
    @Column
    private String model;
    @Transient
    private byte[] image;
    @Column
    private String imageLocation;
    @Column
    private String description;
    @Column
    private String cost;

    /**
     * Getter for property 'id'.
     *
     * @return Value for property 'id'.
     */
    public long getId() {
        return id;
    }

    /**
     * Setter for property 'id'.
     *
     * @param id Value to set for property 'id'.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Getter for property 'brand'.
     *
     * @return Value for property 'brand'.
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Setter for property 'brand'.
     *
     * @param brand Value to set for property 'brand'.
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Getter for property 'type'.
     *
     * @return Value for property 'type'.
     */
    public VehicleType getType() {
        return type;
    }

    /**
     * Setter for property 'type'.
     *
     * @param type Value to set for property 'type'.
     */
    public void setType(VehicleType type) {
        this.type = type;
    }

    /**
     * Getter for property 'model'.
     *
     * @return Value for property 'model'.
     */
    public String getModel() {
        return model;
    }

    /**
     * Setter for property 'model'.
     *
     * @param model Value to set for property 'model'.
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Getter for property 'image'.
     *
     * @return Value for property 'image'.
     */
    public byte[] getImage() {
        return image;
    }

    /**
     * Setter for property 'image'.
     *
     * @param image Value to set for property 'image'.
     */
    public void setImage(byte[] image) {
        this.image = image;
    }

    /**
     * Getter for property 'imageLocation'.
     *
     * @return Value for property 'imageLocation'.
     */
    public String getImageLocation() {
        return imageLocation;
    }

    /**
     * Setter for property 'imageLocation'.
     *
     * @param imageLocation Value to set for property 'imageLocation'.
     */
    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }

    /**
     * Getter for property 'description'.
     *
     * @return Value for property 'description'.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for property 'description'.
     *
     * @param description Value to set for property 'description'.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for property 'cost'.
     *
     * @return Value for property 'cost'.
     */
    public String getCost() {
        return cost;
    }

    /**
     * Setter for property 'cost'.
     *
     * @param cost Value to set for property 'cost'.
     */
    public void setCost(String cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", type=" + type +
                ", model='" + model + '\'' +
                ", image=" + Arrays.toString(image) +
                ", imageLocation='" + imageLocation + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
