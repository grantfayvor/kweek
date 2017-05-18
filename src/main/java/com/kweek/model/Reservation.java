package com.kweek.model;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Harrison on 2017-01-21.
 */

@Entity
@Table
public class Reservation implements Serializable{

    @Id
    @GeneratedValue
    private long id;
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    @JoinColumn(name = "vehicle_id")
    @OneToOne(fetch = FetchType.EAGER)
    private Vehicle vehicle;
    @Column
    private String reservation;
    @Column
    private String description;

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
     * Getter for property 'user'.
     *
     * @return Value for property 'user'.
     */
    public User getUser() {
        return user;
    }

    /**
     * Setter for property 'user'.
     *
     * @param user Value to set for property 'user'.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Getter for property 'vehicle'.
     *
     * @return Value for property 'vehicle'.
     */
    public Vehicle getVehicle() {
        return vehicle;
    }

    /**
     * Setter for property 'vehicle'.
     *
     * @param vehicle Value to set for property 'vehicle'.
     */
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    /**
     * Getter for property 'reservation'.
     *
     * @return Value for property 'reservation'.
     */
    public String getReservation() {
        return reservation;
    }

    /**
     * Setter for property 'reservation'.
     *
     * @param reservation Value to set for property 'reservation'.
     */
    public void setReservation(String reservation) {
        this.reservation = reservation;
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

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", user=" + user +
                ", vehicle=" + vehicle +
                ", reservation='" + reservation + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
