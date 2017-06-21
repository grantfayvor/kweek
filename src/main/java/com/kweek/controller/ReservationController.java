package com.kweek.controller;

import com.kweek.model.AccountType;
import com.kweek.model.Reservation;
import com.kweek.model.User;
import com.kweek.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

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

    @RequestMapping("/user")
    @ResponseBody
    public List<Reservation> getUserReservation(HttpSession httpSession){
        Object user = httpSession.getAttribute("sessionUser");
        if(user instanceof User) {
            if(AccountType.ROLE_USER.equals(((User) user).getAccountType())) {
                return reservationService.findUserReservation(((User) user));
            } else {
                System.out.print("not authorized to perform this function");
                return null;
            }
        }
        else return null;
    }

    @RequestMapping(value = "/new-reservation", method = RequestMethod.POST)
    @ResponseBody
    public Boolean newReservation(@RequestBody Reservation reservation, HttpSession httpSession){
        Object user = httpSession.getAttribute("sessionUser");
        if (user instanceof User) reservation.setUser((User) user);
        return reservationService.save(reservation) != null;
    }
}
