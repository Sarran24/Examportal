package com.examportal.controller;

import com.examportal.config.UserInfoUserDetails;
import com.examportal.model.AuthRequest;
import com.examportal.model.JwtResponse;
import com.examportal.model.User;
import com.examportal.service.JwtService;
import com.examportal.service.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("*")
public class AuthenticateController {

    private final static Logger logger = LoggerFactory.getLogger(AuthenticateController.class);
    @Autowired
    JwtService jwtService;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

//    @PostMapping("/authenticate")
//    public ResponseEntity<String> authenticateAndGetToken(@RequestBody AuthRequest authRequest) throws Exception {
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
//            if (authentication.isAuthenticated()) {
//                String token = jwtService.generateToken(authRequest.getUsername());
//                return ResponseEntity.ok(token) ;
//            } else {
//                throw new UsernameNotFoundException("Invalid user");
//            }
//        } catch (UsernameNotFoundException e) {
//            // log the exception
//            logger.error("Invalid username: " + authRequest.getUsername(), e);
//            throw e;
//        } catch (DisabledException e) {
//            throw new Exception("USER DISABLE" + e.getMessage());
//        } catch (BadCredentialsException e) {
//            throw new Exception("Invalid Credentials" + e.getMessage());
//        }
//    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateAndGetToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            logger.info("Authenticating user: {}", authRequest.getUsername());
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
            if (authentication.isAuthenticated()) {
                String token = jwtService.generateToken(authRequest.getUsername());
                logger.info("User authenticated successfully: {}", authRequest.getUsername());
                return ResponseEntity.ok(new JwtResponse(token)) ;
            } else {
                logger.info("User authentication failed: {}", authRequest.getUsername());
                throw new UsernameNotFoundException("Invalid user");
            }
        } catch (UsernameNotFoundException e) {
            logger.error("Invalid username: " + authRequest.getUsername(), e);
            throw e;
        } catch (DisabledException e) {
            logger.info("User authentication failed: {}", authRequest.getUsername());
            throw new Exception("USER DISABLE" + e.getMessage());
        } catch (BadCredentialsException e) {
            logger.info("User authentication failed: {}", authRequest.getUsername());
            throw new Exception("Invalid Credentials" + e.getMessage());
        }
    }

    @GetMapping("/current-user")
    public ResponseEntity<?> currentUser(Principal principal) {
        try {
            UserInfoUserDetails user = (UserInfoUserDetails) userDetailsService.loadUserByUsername(principal.getName());
            logger.info("user {} sucessfully retrived :",user.getUsername());
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            logger.info("error is  {} :",e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing your request");
        }
    }

}
