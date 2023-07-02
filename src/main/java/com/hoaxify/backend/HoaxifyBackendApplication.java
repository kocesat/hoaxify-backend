package com.hoaxify.backend;

import com.hoaxify.backend.user.User;
import com.hoaxify.backend.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableCaching
@EnableAsync
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
