package com.kweek.controller;

import com.kweek.model.Payment;
import com.kweek.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Harrison on 2017-01-24.
 */

@Controller
@RequestMapping("/api/payment")
public class PaymentController extends AbstractController<Payment>{

    @Autowired
    public PaymentController (PaymentService paymentService){
        super(paymentService);
    }
}
