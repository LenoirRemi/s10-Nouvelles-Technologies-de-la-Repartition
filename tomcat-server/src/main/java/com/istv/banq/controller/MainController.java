package com.istv.banq.controller;


import com.istv.banq.model.History;
import com.istv.banq.model.User;
import com.istv.banq.model.Users;
import com.istv.banq.service.HistoryService;
import com.istv.banq.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class MainController {
    @Autowired
    UserService userService;
    @Autowired
    HistoryService historyService;

    @RequestMapping(value="/home", method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("user", user);
        modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName());
        modelAndView.setViewName("home");
        return modelAndView;
    }

    @RequestMapping(value="/history", method = RequestMethod.GET)
    public ModelAndView history(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("user", user);
        modelAndView.addObject("histories", historyService.listAll());
        modelAndView.setViewName("history");
        return modelAndView;
    }

    @ResponseBody
    @PostMapping(path = "/transaction", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public String creditRequest(@RequestBody List<User> users, BindingResult bindingResult) {
        historyService.saveHistory(users);
        String response = "";
        for (User user:users) {
            userService.saveBalance(user.getId(), user.getBalance());
            if (user.getBalance() < 0){
                response = response + "User " + user.getId() + " was debited with " + user.getBalance() + "\n";
            }
            else {
                response = response + "User " + user.getId() + " was credited with " + user.getBalance() + "\n";
            }
        }
        return response;
    }

    @GetMapping(value = "/users_xml", produces = MediaType.APPLICATION_XML_VALUE)
    public Users findUsers(){
        return userService.findAll();
    }
}
