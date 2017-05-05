package com.kweek.service;

import com.kweek.config.KweekLoggerFactory;
import com.kweek.model.Payment;
import com.kweek.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Harrison on 2017-01-23.
 */

@Service
public class PaymentService extends KweekLoggerFactory implements ServiceInterface<Payment>{

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Payment find(long id) {
        return paymentRepository.findOne(id);
    }

    @Override
    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    @Override
    public Boolean update(Payment payment) {
        return null;
    }

    @Override
    public Boolean delete(long id) {
        return null;
    }

    @Override
    public Boolean delete(Payment payment) {
        return null;
    }

    @Override
    public Page<Payment> findAll(PageRequest pageRequest) {
        return paymentRepository.findAll(pageRequest);
    }

    @Override
    public Page<Payment> findByParam(String param, PageRequest pageRequest) {
        return null;
    }
}
