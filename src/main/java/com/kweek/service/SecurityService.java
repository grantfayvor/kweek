package com.kweek.service;

import com.kweek.config.KweekLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by Harrison on 2017-04-26.
 */

@Service
public class SecurityService extends KweekLoggerFactory{

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean authenticate(String username, String password) throws AuthenticationException{
        UserDetails authenticatedUser = this.confirmUserPassword(username, password);
        if (authenticatedUser != null){
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authenticatedUser, password, authenticatedUser.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            return true;
        } else {
            logger.config("=== " +username+ " could not be authenticated in the kweek system ===");
            throw new InternalAuthenticationServiceException(username + " could not be authenticated in the kweek system");
        }
    }

    private UserDetails confirmUserPassword(String username, String presentedPassword) throws AuthenticationException {
        UserDetails loadedUser = userService.loadUserByUsername(username);
        if (loadedUser != null) {
            if(passwordEncoder.matches(presentedPassword, loadedUser.getPassword())){
                return loadedUser;
            } else {
                logger.config("=== loaded user password could not be confirmed ===");
                throw new InternalAuthenticationServiceException("invalid password..Password does not match");
            }
        } else {
            logger.config("=== no user was found in the database where username is " +username+ " ===");
            throw new InternalAuthenticationServiceException(
                    "UserDetailsService returned null, which is an interface contract violation");
        }
    }
}
