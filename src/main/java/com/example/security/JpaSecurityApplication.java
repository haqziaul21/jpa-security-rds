package com.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class JpaSecurityApplication implements CommandLineRunner {

	@Autowired
	private UserRepo repo;

	public static void main(String[] args) {
		SpringApplication.run(JpaSecurityApplication.class, args);
	}

	@GetMapping("/")
	public String getMapping() {
		return "<h1>ALL</h1>";
	}

	@GetMapping("/user")
	public String getUSer() {
		return "<h1>USER</h1>";
	}

	@GetMapping("/admin")
	public String getAdmin() {
		return "<h1>ADMIN</h1>";
	}

	@Override
	public void run(String... args) throws Exception {
		Users user;
		user = new Users();

		user.setName("Ziaul");
		user.setActive(false);
		user.setPassword("123");
		user.setRoles("ROLE_ADMIN,ROLE_USER");

		repo.save(user);

		user = new Users();
		user.setName("admin");
		user.setActive(true);
		user.setPassword("admin");
		user.setRoles("ROLE_ADMIN");

		repo.save(user);

		user = new Users();

		user.setName("user");
		user.setActive(true);
		user.setPassword("user");
		user.setRoles("ROLE_USER");

		repo.save(user);
	}
}
