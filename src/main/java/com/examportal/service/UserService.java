package com.examportal.service;

import com.examportal.model.User;
import com.examportal.model.UserRole;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.Set;

public interface UserService {

    public Optional<User> createUser(User user, Set<UserRole> userRoles) throws Exception;

    public Optional<User> getByUsername(String username);

    public Optional<User> updateUser(User user);

    public String deleteUser(String username);
}
