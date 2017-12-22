package com.kweek.model;

import javax.persistence.*;

/**
 * Created by Harrison on 2017-08-10.
 */

@Entity
@Table(name = "feedback")
public class Feedback {

    @Id
    @GeneratedValue
    private long id;
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    @Column
    private String message;

    public Feedback() {
    }

    public Feedback(User user, String message) {
        this.user = user;
        this.message = message;
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
     * Getter for property 'message'.
     *
     * @return Value for property 'message'.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Setter for property 'message'.
     *
     * @param message Value to set for property 'message'.
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
