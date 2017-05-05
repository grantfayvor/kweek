package com.kweek.controller;

import com.kweek.model.User;
import com.kweek.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


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

}
