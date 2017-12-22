package com.kweek.model;

import javax.persistence.*;

/**
 * Created by Harrison on 2017-10-17.
 */

@Entity
@Table(name = "ride")
public class Ride {

    @Id
    @GeneratedValue
    private long id;
    @JoinColumn(name = "driver_id")
    @OneToOne(fetch = FetchType.EAGER)
    private Driver driver;
    @JoinColumn(name = "user_id")
    @OneToOne(fetch = FetchType.EAGER)
    private User user;
    @Column
    private long cost;
    @JoinColumn(name = "feedback_id")
    @OneToOne(fetch = FetchType.EAGER)
    private Feedback feedback;

    public Ride() {

    }

    public Ride(User user, Driver driver) {
        this.user = user;
        this.driver = driver;
    }

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
     * Getter for property 'driver'.
     *
     * @return Value for property 'driver'.
     */
    public Driver getDriver() {
        return driver;
    }

    /**
     * Setter for property 'driver'.
     *
     * @param driver Value to set for property 'driver'.
     */
    public void setDriver(Driver driver) {
        this.driver = driver;
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
     * Getter for property 'cost'.
     *
     * @return Value for property 'cost'.
     */
    public long getCost() {
        return cost;
    }

    /**
     * Setter for property 'cost'.
     *
     * @param cost Value to set for property 'cost'.
     */
    public void setCost(long cost) {
        this.cost = cost;
    }

    /**
     * Getter for property 'feedback'.
     *
     * @return Value for property 'feedback'.
     */
    public Feedback getFeedback() {
        return feedback;
    }

    /**
     * Setter for property 'feedback'.
     *
     * @param feedback Value to set for property 'feedback'.
     */
    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }
}
