package com.hoaxify.hoaxifybackend;

import com.hoaxify.hoaxifybackend.user.User;
import com.hoaxify.hoaxifybackend.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication()
public class HoaxifyBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(HoaxifyBackendApplication.class, args);
	}

	@Bean
	CommandLineRunner createInitialUsers(UserService userService) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				User user = new User();
				user.setUsername("user1");
				user.setDisplayName("display 1");
				user.setPassword("Pass1234");
				userService.createUser(user);
			}
		};
	}

}
