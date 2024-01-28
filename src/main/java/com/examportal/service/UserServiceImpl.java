package com.examportal.service;

import com.examportal.exception.UserExistsException;
import com.examportal.exception.UserNotExistException;
import com.examportal.model.User;
import com.examportal.model.UserRole;
import com.examportal.repository.RoleRepository;
import com.examportal.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;



    @Override
    public Optional<User> createUser(User user, Set<UserRole> userRoles) throws Exception {
        Optional<User> local = this.userRepository.findByUsername(user.getUsername());

        if (local.isPresent()) {
            logger.info("user is already present: {}", local);
            throw new UserExistsException("user already exist");
        }
        userRoles.stream().map(UserRole::getRole).forEach(roleRepository::save);
        user.getUserRoleset().addAll(userRoles);
        local = Optional.of(this.userRepository.save(user));
        logger.info("user details has been saved {} :", local);
        return local;
    }

    @Override
    public Optional<User> getByUsername(String username) {
        return Optional.ofNullable(userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotExistException("Sorry, there is no user with this username")));
    }

    @Override
    public Optional<User> updateUser(User user) {
        Optional<User> local = this.userRepository.findByUsername(user.getUsername());
        if (local.isPresent()) {
            User updatedUser = local.get();
            updatedUser.setFirstName(user.getFirstName());
            updatedUser.setLastName(user.getLastName());
            updatedUser.setPhone(user.getPhone());
            updatedUser.setEmail(user.getEmail());
            updatedUser.setProfile(user.getProfile());
            this.userRepository.save(updatedUser);
            return local;
        } else {
            throw new UserNotExistException("no user exist");
        }


    }

    @Override
    public String deleteUser(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isPresent()){
            userRepository.deleteByUsername(username);
            return "deleted";
        }
        throw new UserNotExistException("no user exist");
    }


}
