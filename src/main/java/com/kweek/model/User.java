package com.kweek.model;

import org.hibernate.validator.constraints.Email;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Created by Harrison on 2017-01-21.
 */

@Entity
@Table
public class User implements UserDetails, Serializable {

    @Id
    @GeneratedValue
    @Column
    private long id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column(name = "user_name")
    private String username;
    @Transient
    private String rawPassword;
    @Column
    private String password;
    @Column
    @Email(message = "enter a valid email address")
    private String email;
    @Column
//    @Pattern(regexp = "[+]\\d{3}[ ]\\d{10}", message = "enter a valid phone number")
    private String phoneNumber;
    @Column
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @Transient
    private List<Reservation> reservations;

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
     * Getter for property 'firstName'.
     *
     * @return Value for property 'firstName'.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter for property 'firstName'.
     *
     * @param firstName Value to set for property 'firstName'.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter for property 'lastName'.
     *
     * @return Value for property 'lastName'.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter for property 'lastName'.
     *
     * @param lastName Value to set for property 'lastName'.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getter for property 'username'.
     *
     * @return Value for property 'username'.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter for property 'username'.
     *
     * @param username Value to set for property 'username'.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter for property 'rawPassword'.
     *
     * @return Value for property 'rawPassword'.
     */
    public String getRawPassword() {
        return rawPassword;
    }

    /**
     * Setter for property 'rawPassword'.
     *
     * @param rawPassword Value to set for property 'rawPassword'.
     */
    public void setRawPassword(String rawPassword) {
        this.rawPassword = rawPassword;
    }

    /**
     * Getter for property 'password'.
     *
     * @return Value for property 'password'.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for property 'password'.
     *
     * @param password Value to set for property 'password'.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter for property 'email'.
     *
     * @return Value for property 'email'.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter for property 'email'.
     *
     * @param email Value to set for property 'email'.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter for property 'phoneNumber'.
     *
     * @return Value for property 'phoneNumber'.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Setter for property 'phoneNumber'.
     *
     * @param phoneNumber Value to set for property 'phoneNumber'.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Getter for property 'accountType'.
     *
     * @return Value for property 'accountType'.
     */
    public AccountType getAccountType() {
        return accountType;
    }

    /**
     * Setter for property 'accountType'.
     *
     * @param accountType Value to set for property 'accountType'.
     */
    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    /**
     * Getter for property 'reservations'.
     *
     * @return Value for property 'reservations'.
     */
    public List<Reservation> getReservations() {
        return reservations;
    }

    /**
     * Setter for property 'reservations'.
     *
     * @param reservations Value to set for property 'reservations'.
     */
    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", rawPassword='" + rawPassword + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", accountType=" + accountType +
                '}';
    }
}
