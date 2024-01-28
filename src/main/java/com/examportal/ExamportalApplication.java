package com.examportal;

import com.examportal.model.Role;
import com.examportal.model.User;
import com.examportal.model.UserRole;
import com.examportal.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@SpringBootApplication
public class ExamportalApplication implements CommandLineRunner {
	@Autowired
	UserServiceImpl userService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ExamportalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		User user = new User();
//		user.setUsername("sarran24");
//		user.setEmail("fahyien@gmail.com");
//		user.setEnable(true);
//		user.setFirstName("Sarran");
//		user.setLastName("Sher");
//		user.setPassword(this.bCryptPasswordEncoder.encode("fahyien"));
//		user.setProfile("default.png");
//		user.setPhone("9304557946");
//		Role role = new Role();
//		role.setRoleId(10L);
//		role.setRoleName("ADMIN");
//		UserRole userRole = new UserRole();
//		userRole.setRole(role);
//		userRole.setUser(user);
//		Set<UserRole> userRoles = new HashSet<>();
//		userRoles.add(userRole);
//		Optional<User> user1 = this.userService.createUser(user, userRoles);
//		System.out.println(user1.get().getUsername());
	}

}
