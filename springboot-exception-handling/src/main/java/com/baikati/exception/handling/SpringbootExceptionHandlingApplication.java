package com.baikati.exception.handling;

import com.baikati.exception.handling.entity.User;
import com.baikati.exception.handling.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class SpringbootExceptionHandlingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootExceptionHandlingApplication.class, args);
    }

    @Component
    @AllArgsConstructor
    class DataInitializer implements CommandLineRunner {
        private UserService userService;

        @Override
        public void run(String... args) throws Exception {
            userService.createUser(new User(null, "Ishitha", "Ishitha@gmail.com"));
            userService.createUser(new User(null, "Pawan", "pawan@gmail.com"));
        }
    }
}
