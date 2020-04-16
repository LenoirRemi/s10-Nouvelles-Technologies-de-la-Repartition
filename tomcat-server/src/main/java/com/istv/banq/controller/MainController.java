package com.istv.banq.controller;

import com.istv.banq.model.User;
import com.istv.banq.model.Users;
import com.istv.banq.service.HistoryService;
import com.istv.banq.service.UserService;
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

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("user", user);
        modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName());
        modelAndView.setViewName("home");
        return modelAndView;
    }

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public ModelAndView history() {
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
        String response = "";
        float balance_user1 = 0;
        float balance_user2 = 0;
        int id_user1 = 0;
        int id_user2 = 0;
        int cpt = 0;

        if (users.size() > 2) {
            return "Erreur dans la transaction ! Une transaction doit etre realise entre 2 users pas plus !";
        }
        if (users.size() == 1) {
            id_user2 = -1;
            users.add(userService.findUserByname("webservice"));
        }

        for (User user : users) {
            System.out.println(user);
            if (cpt == 0) {
                id_user1 = user.getId();
                balance_user1 = user.getBalance();
                System.out.println("balance user1: "+balance_user1);
            } else if (cpt == 1) {
                id_user2 = user.getId();
                balance_user2 = user.getBalance();
            }

            if (balance_user1 != 0 && balance_user2 != 0) {
                if (balance_user1 == balance_user2 || balance_user1 + balance_user2 != 0) {
                    return "Erreur dans les balances ! Ex: user id 1 recoit 100 alors le user id 2 doit perdre 100";
                }
            }

            if (user.getBalance() < 0) {
                response = response + "User " + user.getId() + " was debited with " + user.getBalance() + "\n";
            } else {
                response = response + "User " + user.getId() + " was credited with " + user.getBalance() + "\n";
            }
            cpt++;
        }

        historyService.saveHistory(users);
        userService.saveBalance(id_user1, balance_user1);
        userService.saveBalance(id_user2, balance_user2);

        return response;
    }


    @GetMapping(value = "/users_xml", produces = MediaType.APPLICATION_XML_VALUE)
    public Users findUsers(){
        return userService.findAll();
    }

}
