package com.kweek.controller;

import com.kweek.model.Feedback;
import com.kweek.model.User;
import com.kweek.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * Created by Harrison on 2017-01-24.
 */

@Controller
@RequestMapping("/api/user")
public class UserController extends AbstractController<User>{

    @Autowired
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        super(userService);
    }

    @RequestMapping("/feedback/all")
    @ResponseBody
    public List<Feedback> findFeedbacks(){
        return userService.getFeedbacks();
    }

    @RequestMapping(value = "/feedback", method = RequestMethod.POST)
    @ResponseBody
    public boolean addFeedback(@RequestBody String message, HttpSession httpSession){
        Object user = httpSession.getAttribute("sessionUser");
        if(user instanceof User) {
            Feedback feedback = new Feedback((User) user, message);
            return userService.addFeedback(feedback);
        } else {
            return false;
        }
    }

}
