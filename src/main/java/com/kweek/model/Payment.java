package com.kweek.model;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * Created by Harrison on 2017-01-21.
 */

@Entity
@Table
public class Payment implements Serializable{

    @Id
    @GeneratedValue
    private long id;
    @OneToOne
    @JoinColumn
    private Reservation reservationId;
    @Column
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
    @Transient
    @NotBlank
    @Pattern(regexp = "\\d{3}", message = "Invalid  CCV number")
    private String ccvNumber;
    @Transient
    @NotBlank
    @Pattern(regexp = "\\d{4}[-]\\d{4}[-]\\d{4}[-]\\d{4}", message = "Invalid  card number")
    private String cardNumber;
    @Transient
    @NotBlank
    @Pattern(regexp = "\\d{2}[/]\\d{4}", message = "Invalid  card expiry date")
    private String expiryDate;
    @Column
    private double amountPayed;

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
     * Getter for property 'reservationId'.
     *
     * @return Value for property 'reservationId'.
     */
    public Reservation getReservationId() {
        return reservationId;
    }

    /**
     * Setter for property 'reservationId'.
     *
     * @param reservationId Value to set for property 'reservationId'.
     */
    public void setReservationId(Reservation reservationId) {
        this.reservationId = reservationId;
    }

    /**
     * Getter for property 'paymentType'.
     *
     * @return Value for property 'paymentType'.
     */
    public PaymentType getPaymentType() {
        return paymentType;
    }

    /**
     * Setter for property 'paymentType'.
     *
     * @param paymentType Value to set for property 'paymentType'.
     */
    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * Getter for property 'ccvNumber'.
     *
     * @return Value for property 'ccvNumber'.
     */
    public String getCcvNumber() {
        return ccvNumber;
    }

    /**
     * Setter for property 'ccvNumber'.
     *
     * @param ccvNumber Value to set for property 'ccvNumber'.
     */
    public void setCcvNumber(String ccvNumber) {
        this.ccvNumber = ccvNumber;
    }

    /**
     * Getter for property 'cardNumber'.
     *
     * @return Value for property 'cardNumber'.
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Setter for property 'cardNumber'.
     *
     * @param cardNumber Value to set for property 'cardNumber'.
     */
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * Getter for property 'expiryDate'.
     *
     * @return Value for property 'expiryDate'.
     */
    public String getExpiryDate() {
        return expiryDate;
    }

    /**
     * Setter for property 'expiryDate'.
     *
     * @param expiryDate Value to set for property 'expiryDate'.
     */
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * Getter for property 'amountPayed'.
     *
     * @return Value for property 'amountPayed'.
     */
    public double getAmountPayed() {
        return amountPayed;
    }

    /**
     * Setter for property 'amountPayed'.
     *
     * @param amountPayed Value to set for property 'amountPayed'.
     */
    public void setAmountPayed(double amountPayed) {
        this.amountPayed = amountPayed;
    }
}
