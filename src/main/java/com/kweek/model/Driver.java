package com.kweek.model;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.io.Serializable;

/**
 * Created by Harrison on 2017-06-14.
 */

@Entity
@Table(name = "driver")
public class Driver implements Serializable{

    @Id
    @GeneratedValue
    private long id;
    @JoinColumn(name = "user_id")
    @OneToOne(fetch = FetchType.EAGER)
    private User user;
    @Column
    private boolean ready;
    @Column
    private double lat;
    @Column
    private double lng;

    public Driver() {
    }

    public Driver(User user, boolean ready) {
        this.user = user;
        this.ready = ready;
    }

    public Driver(User user, boolean ready, Coordinates coordinates) {
        this.user = user;
        this.ready = ready;
        this.lat = coordinates.getLat();
        this.lng = coordinates.getLng();
    }

    /** {@inheritDoc} */
    public long getId() {
        return id;
    }

    /** {@inheritDoc} */
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
     * Getter for property 'ready'.
     *
     * @return Value for property 'ready'.
     */
    public boolean isReady() {
        return ready;
    }

    /**
     * Setter for property 'ready'.
     *
     * @param ready Value to set for property 'ready'.
     */
    public void setReady(boolean ready) {
        this.ready = ready;
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
        return "Driver{" +
                "id=" + id +
                ", user=" + user +
                ", ready=" + ready +
                ", latitude=" + lat +
                ", longitude=" + lng +
                '}';
    }
}
