package com.istv.banq.controller;

import com.istv.banq.model.History;
import com.istv.banq.model.User;
import com.istv.banq.service.HistoryService;
import com.istv.banq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
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

}
