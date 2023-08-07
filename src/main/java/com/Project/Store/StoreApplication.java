package com.Project.Store;

import com.Project.Store.entity.ERole;
import com.Project.Store.entity.Role;
import com.Project.Store.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StoreApplication  implements CommandLineRunner {
	@Autowired
	RoleRepository roleRepository;
	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Role admin = new Role(1L, ERole.ROLE_ADMIN);
		Role user = new Role(2L, ERole.ROLE_USER);
		roleRepository.save(admin);
		roleRepository.save(user);
	}
}
