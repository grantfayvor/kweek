package com.kweek.controller;

import com.kweek.model.Reservation;
import com.kweek.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Harrison on 2017-01-24.
 */

@Controller
@RequestMapping("/api/reservation")
public class ReservationController extends AbstractController<Reservation>{

    @Autowired
    private ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        super(reservationService);
    }

    @RequestMapping("/{userId}")
    @ResponseBody
    public Reservation getUserReservation(@PathVariable("userId") long id){
        return reservationService.findUserReservation(id);
    }
}
