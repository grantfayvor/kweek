package com.kweek.service;

import com.kweek.config.KweekLoggerFactory;
import com.kweek.model.AccountType;
import com.kweek.model.Driver;
import com.kweek.model.Feedback;
import com.kweek.model.User;
import com.kweek.repository.DriverRepository;
import com.kweek.repository.FeedbackRepository;
import com.kweek.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Harrison on 2017-01-24.
 */

@Service
public class UserService extends KweekLoggerFactory implements UserDetailsService, ServiceInterface<User>{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserService(){
        super();
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getRawPassword()));
        User addedUser;
        if(AccountType.ROLE_DRIVER.equals(user.getAccountType())){
            addedUser = userRepository.save(user);
            Driver driver = new Driver(user, false);
            driverRepository.save(driver);
        } else {
            user.setAccountType(AccountType.ROLE_USER);
            addedUser = userRepository.save(user);
        }
        return addedUser;
    }

    @Override
    public User find(long id) {
        return userRepository.findOne(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> findAll(PageRequest pageRequest) {
        return userRepository.findAll(pageRequest);
    }

    @Override
    public Boolean update(User user) {
        return null;
    }

    @Override
    public void delete(long id) {
        userRepository.delete(id);
    }

    @Override
    public Boolean delete(User user) {
        return null;
    }

    @Override
    public Page<User> findByParam(String param, PageRequest pageRequest) {
        return userRepository.findByParam(param, pageRequest);
    }

    public boolean addFeedback(Feedback feedback){
        return feedbackRepository.save(feedback) != null;
    }

    public List<Feedback> getFeedbacks(){
        return  feedbackRepository.findAll();
    }

}
