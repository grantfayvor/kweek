package com.kweek.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by Harrison on 2017-01-21.
 */
public enum AccountType implements GrantedAuthority{
    ROLE_USER, ROLE_DRIVER, ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return null;
    }


}
