package com.kweek.controller;

import com.kweek.model.AccountType;
import com.kweek.model.Authority;
import com.kweek.model.User;
import com.kweek.service.SecurityService;
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
    private SecurityService securityService;

    @RequestMapping({"/", "/home"})
    public String index(HttpSession httpSession) {
        Object object = httpSession.getAttribute("sessionUser");
        if (object instanceof User) {
            if (AccountType.ROLE_USER.equals(((User) object).getAccountType())) return "index";
            else if (AccountType.ROLE_ADMIN.equals(((User) object).getAccountType())) return "admin/index";
            else if (AccountType.ROLE_DRIVER.equals(((User) object).getAccountType())) return "driver/index";
            else return "index";
        } else return "index";
    }

    @RequestMapping(value = "/login")
    public String login(HttpSession httpSession) {
        if ("anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) return "login";
        else return "redirect:/";
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    @ResponseBody
    public Authority authenticate(@RequestBody User user, HttpSession httpSession) {
        User authenticatedUser = (User) securityService.authenticate(user.getUsername(), user.getPassword());
        if (authenticatedUser != null) {
            httpSession.setAttribute("sessionUser", authenticatedUser);
            return new Authority(authenticatedUser.getAccountType());
        } else {
            return null;
        }
    }

    @RequestMapping("/logout")
    @ResponseBody
    public void logout(Model model, HttpSession httpSession) {
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
    public String acceptRide(HttpSession httpSession) {
        Object user = httpSession.getAttribute("sessionUser");
        if (user instanceof User && AccountType.ROLE_DRIVER.equals(((User) user).getAccountType())) {
            return "driver/accept-ride";
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
