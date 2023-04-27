package com.example.enset;

import com.example.enset.entities.Role;
import com.example.enset.entities.User;
import com.example.enset.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class UsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersApplication.class, args);
	}

	@Bean
	CommandLineRunner start(UserService userService){
		return args -> {
			User user = new User();
			user.setUserName("user1");
			user.setPassword("123456");
			userService.addNewUser(user);

			User admin = new User();
			admin.setUserName("admin");
			admin.setPassword("123456");
			userService.addNewUser(admin);

			Stream.of("STUDENT", "USER", "ADMIN").forEach(r->{
				Role role = new Role();
				role.setRoleName(r);
				role.setDescription("Description");
				userService.addNewRole(role);
			});

			userService.addRoleToUser("user1", "STUDENT");
			userService.addRoleToUser("user1", "USER");
			userService.addRoleToUser("admin", "USER");
			userService.addRoleToUser("admin", "ADMIN");

			try {
				User authenticatedUser = userService.authenticate("user1", "123456");
				System.out.println(authenticatedUser.getUserId());
				System.out.println(authenticatedUser.getUserName());
				System.out.println("Roles ==> ");
				authenticatedUser.getRoles().forEach(role->{
					System.out.println("Role => " + role);
				});
			}
			catch (Exception e){
				e.printStackTrace();
			}

		};

	}
}
