package com.kweek.repository;

import com.kweek.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Harrison on 2017-08-10.
 */

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long>{
}
