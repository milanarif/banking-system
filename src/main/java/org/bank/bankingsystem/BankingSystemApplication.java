package org.bank.bankingsystem;

import org.bank.bankingsystem.entity.RoleEntity;
import org.bank.bankingsystem.entity.UserEntity;
import org.bank.bankingsystem.repository.RoleRepository;
import org.bank.bankingsystem.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BankingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingSystemApplication.class, args);
	}

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	@Bean
    public CommandLineRunner systemInit(RoleRepository roleRepository, UserRepository userRepository) {
        return (args) -> {
           RoleEntity findRoles = roleRepository.findByName("ROLE_ADMIN");
           if (findRoles == null) {
               roleRepository.save(new RoleEntity("ROLE_ADMIN"));
               roleRepository.save(new RoleEntity("ROLE_USER"));

                UserEntity admin = new UserEntity();
                admin.setName("Admin");
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder().encode("admin"));
                admin.addRole(roleRepository.findByName("ROLE_ADMIN"));
                userRepository.save(admin);
           }
       };
    }
}
