package com.Project.Store;


import com.Project.Store.entity.ERole;
import com.Project.Store.entity.Role;
import com.Project.Store.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StoreApplication  implements CommandLineRunner {
    @Autowired
    IRoleRepository roleRepository;
    public static void main(String[] args) {
        SpringApplication.run(StoreApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        Role user = new Role(1L, ERole.ADMIN);
        Role admin = new Role(2L, ERole.USER);
        roleRepository.save(user);
        roleRepository.save(admin);
    }
}
