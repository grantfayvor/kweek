package com.kweek.controller;

import com.kweek.model.AccountType;
import com.kweek.model.Authority;
import com.kweek.model.Coordinates;
import com.kweek.model.User;
import com.kweek.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    private SecurityService securityService;

    @RequestMapping("/home")
    public String index(HttpSession httpSession){
        return "index";
    }

    @RequestMapping("/")
    public String dashboard(HttpSession httpSession) {
        Object object = httpSession.getAttribute("sessionUser");
        if (object instanceof User) {
            if (AccountType.ROLE_USER.equals(((User) object).getAccountType())) return "dashboard";
            else if (AccountType.ROLE_ADMIN.equals(((User) object).getAccountType())) return "admin/dashboard";
            else if (AccountType.ROLE_DRIVER.equals(((User) object).getAccountType())) return "driver/dashboard";
            else return "dashboard";
        } else return "dashboard";
    }

    @RequestMapping(value = "/login")
    public String login(HttpSession httpSession) {
        if ("anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) return "login";
        else return "redirect:/";
    }

    @RequestMapping(value = "/signup")
    public String register(HttpSession httpSession) {
        return "register";
    }

    @RequestMapping(value = "/signup-driver")
    public String registerDriver(HttpSession httpSession) {
        return "driver/register";
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    @ResponseBody
    public AccountType authenticate(@RequestBody User user, HttpSession httpSession) {
        User authenticatedUser = (User) securityService.authenticate(user.getUsername(), user.getPassword());
        if (authenticatedUser != null) {
            httpSession.setAttribute("sessionUser", authenticatedUser);
//            httpSession.setAttribute("role", authenticatedUser.getAccountType());
            return authenticatedUser.getAccountType();
        } else {
            return null;
        }
    }

    @RequestMapping("/logout")
    public String logout(Model model, HttpSession httpSession) {
        if (httpSession != null) {
            if (model != null) {
                model.asMap().clear();
            }
            do {
                httpSession.removeAttribute(httpSession.getAttributeNames().nextElement());
            } while (httpSession.getAttributeNames().hasMoreElements());
            httpSession.invalidate();
            SecurityContextHolder.clearContext();
        }
        return "redirect:/login";
    }

    @RequestMapping("/admin")
    public String admin(HttpSession httpSession) {
        Object user = httpSession.getAttribute("sessionUser");
        if (user instanceof User && AccountType.ROLE_ADMIN.equals(((User) user).getAccountType())) {
            return "admin-home";
        } else {
            return "error-403";
        }
    }

    @RequestMapping("/accept-ride")
//    public String acceptRide(@RequestParam("coordinates") Coordinates coordinates, HttpSession httpSession) {
    public String acceptRide(HttpSession httpSession) {
        Object user = httpSession.getAttribute("sessionUser");
        if (user instanceof User && AccountType.ROLE_DRIVER.equals(((User) user).getAccountType())) {
//            model.addAttribute("coordinates", coordinates);
            return "driver/accept-ride";
        } else {
            return "error-403";
        }
    }

    /*@RequestMapping("/error")
    public String error(){
        return "error";
    }*/

    @RequestMapping("/token")
    @ResponseBody
    public Map<String, String> token(HttpSession httpSession) {
        return Collections.singletonMap("token", httpSession.getId());
    }
}
