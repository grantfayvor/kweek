package com.kweek.model;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

/**
 * Created by Harrison on 2017-01-21.
 */

@Entity
@Table
public class Reservation {

    @Id
    @GeneratedValue
    private long id;
    @JoinColumn
    @OneToOne
    @Cascade(value = CascadeType.ALL)
    private User userId;
    @JoinColumn
    @OneToMany(fetch = FetchType.LAZY)
    @Cascade(value = CascadeType.ALL)
    private List<Vehicle> vehiclesId;
    @Column
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date reservationDate;
    //add this field to the migration script and also add the setter and getter
    /*@Column
    private String description;*/
    @Column
    private double cost;

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
     * Getter for property 'userId'.
     *
     * @return Value for property 'userId'.
     */
    public User getUserId() {
        return userId;
    }

    /**
     * Setter for property 'userId'.
     *
     * @param userId Value to set for property 'userId'.
     */
    public void setUserId(User userId) {
        this.userId = userId;
    }

    /**
     * Getter for property 'vehiclesId'.
     *
     * @return Value for property 'vehiclesId'.
     */
    public List<Vehicle> getVehiclesId() {
        return vehiclesId;
    }

    /**
     * Setter for property 'vehiclesId'.
     *
     * @param vehiclesId Value to set for property 'vehiclesId'.
     */
    public void setVehiclesId(List<Vehicle> vehiclesId) {
        this.vehiclesId = vehiclesId;
    }

    /**
     * Getter for property 'reservationDate'.
     *
     * @return Value for property 'reservationDate'.
     */
    public Date getReservationDate() {
        return reservationDate;
    }

    /**
     * Setter for property 'reservationDate'.
     *
     * @param reservationDate Value to set for property 'reservationDate'.
     */
    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    /**
     * Getter for property 'cost'.
     *
     * @return Value for property 'cost'.
     */
    public double getCost() {
        return cost;
    }

    /**
     * Setter for property 'cost'.
     *
     * @param cost Value to set for property 'cost'.
     */
    public void setCost(double cost) {
        this.cost = cost;
    }
}
