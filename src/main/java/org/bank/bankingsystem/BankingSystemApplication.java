package org.bank.bankingsystem;

import org.bank.bankingsystem.entity.RoleEntity;
import org.bank.bankingsystem.repository.RoleRepository;
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
    public CommandLineRunner setUpRoles(RoleRepository roleRepository) {
        return (args) -> {
           RoleEntity findRoles = roleRepository.findByName("ROLE_ADMIN");
           if (findRoles == null) {
               roleRepository.save(new RoleEntity("ROLE_ADMIN"));
               roleRepository.save(new RoleEntity("ROLE_USER"));
           }
       };
    }

}
