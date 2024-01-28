package com.examportal.controller;

import com.examportal.model.Role;
import com.examportal.model.User;
import com.examportal.model.UserRole;
import com.examportal.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/")
    public ResponseEntity<Optional<User>> createUser(@RequestBody User user) throws Exception {
        user.setProfile("default.png");
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        Role role = new Role();
        role.setRoleId(11L);
        role.setRoleName("NORMAL");
        UserRole userRole = new UserRole();
        userRole.setRole(role);
        userRole.setUser(user);
        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(userRole);
        Optional<User> userResponse = userService.createUser(user,userRoles);
        logger.info("user data saved {} :",userResponse);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }



    @GetMapping("/get/{username}")
    public ResponseEntity<Optional<User>> getByUsername(@PathVariable String username){
        Optional<User> userResponse =  userService.getByUsername(username);
        return new ResponseEntity<>(userResponse,HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Optional<User>> updateUser(@RequestBody User user){
        Optional<User> userResponse = userService.updateUser(user);
        return new ResponseEntity<>(userResponse,HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@PathVariable String username) {
        String msg = userService.deleteUser(username);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
}
