package com.kweek.repository;

import com.kweek.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Harrison on 2017-01-23.
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    User findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.firstName LIKE %?1% OR u.lastName LIKE %?1% OR u.username LIKE %?1% OR u.email LIKE %?1% " +
            "OR u.phoneNumber LIKE %?1%")
    Page<User> findByParam(String param, Pageable pageable);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u = ?1 WHERE u.username = ?2 AND u.password = ?3")
    Integer updateUsernameAndPassword(User user, String username, String password);
}
