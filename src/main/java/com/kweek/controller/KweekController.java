package com.kweek.controller;

import com.kweek.model.AccountType;
import com.kweek.model.User;
import com.kweek.service.SecurityService;
import com.kweek.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Map;

/**
 * Created by Harrison on 2017-04-06.
 */

@Controller
@RequestMapping("")
public class KweekController {

    @Autowired
    private UserService userService;
    @Autowired
    private SecurityService securityService;

    @RequestMapping({"/", "/home"})
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/login")
    public String login(HttpSession httpSession){
        return "login";
        /*if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) return "login";
        else return "redirect:/";*/
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    @ResponseBody
    public boolean authenticate(@RequestBody User user, HttpSession httpSession){
        User authenticatedUser = (User) securityService.authenticate(user.getUsername(), user.getPassword());
        if(authenticatedUser != null){
            httpSession.setAttribute("sessionUser", authenticatedUser);
            return true;
        } else {
            return false;
        }
    }

    @RequestMapping("/logout")
    @ResponseBody
    public void logout(Model model, HttpSession httpSession) {
        model.asMap().clear();
        do {
            httpSession.removeAttribute(httpSession.getAttributeNames().nextElement());
        } while(httpSession.getAttributeNames().hasMoreElements());
        httpSession.invalidate();
        SecurityContextHolder.clearContext();
    }

    @RequestMapping("/admin")
    public String admin(HttpSession httpSession){
        Object user = httpSession.getAttribute("sessionUser");
        if(user instanceof User && AccountType.ROLE_ADMIN.equals(((User) user).getAccountType())) {
            return "admin-home";
        } else {
            return "error-403";
        }
    }

    @RequestMapping("/token")
    @ResponseBody
    public Map<String, String> token(HttpSession httpSession) {
        return Collections.singletonMap("token", httpSession.getId());
    }
}
