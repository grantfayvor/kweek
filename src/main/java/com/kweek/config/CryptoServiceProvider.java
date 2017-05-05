package com.kweek.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;

/**
 * Created by Harrison on 2017-02-05.
 */
@Deprecated
public class CryptoServiceProvider extends KweekLoggerFactory {

    @Value("${crypt.password.strength}")
    private static int PASSWORD_STRENGTH;
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(PASSWORD_STRENGTH, new SecureRandom());

    public static String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public static boolean comparePasswordWithHash(String password, String passwordHash) {
        return passwordEncoder.matches(password, passwordHash);
    }

}
