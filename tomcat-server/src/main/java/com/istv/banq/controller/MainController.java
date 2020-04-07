package com.istv.banq.controller;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.istv.banq.model.History;
import com.istv.banq.model.User;
import com.istv.banq.service.HistoryService;
import com.istv.banq.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.awt.*;
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
        modelAndView.addObject("histories", historyService.listAll());
        modelAndView.setViewName("history");
        return modelAndView;
    }

    @ResponseBody
    @PostMapping(path = "/credit", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public String creditRequest(@RequestBody User user, BindingResult bindingResult) {
        //User userRest = new User();
        //BeanUtils.copyProperties(user, userRest);
        //System.out.println("controler, user xml : "+userRest);
        userService.saveBalance(user.getName(), user.getBalance());
        //System.out.println("saveBalance called");

        return "Account credited";
    }
}
