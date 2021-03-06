package com.istv.banq.service;

import com.istv.banq.model.Role;
import com.istv.banq.model.User;
import com.istv.banq.model.Users;
import com.istv.banq.repository.RoleRepository;
import com.istv.banq.repository.UserRepository;
import com.istv.banq.repository.UserRepositoryCrudJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service("userService")
public class UserService {

    private RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserRepositoryCrudJson userRepositoryCrudJson;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(){

    }

    @Autowired
    public UserService(RoleRepository roleRepository, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public User findUserByname(String name) {
        return userRepository.findByName(name);
    }

    public List<User> listAll() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public Users findAll() {
        List<User> users = (List<User>) userRepositoryCrudJson.findAll();
        Users myUsers = new Users();
        myUsers.setUsers(users);

        return myUsers;
    }

    public void saveBalance(int id, float balance){
        User user = userRepository.findById(id);
        float actual_balance = user.getBalance();
        user.setBalance(actual_balance + balance);
        userRepository.saveAndFlush(user);
    }

    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

}
