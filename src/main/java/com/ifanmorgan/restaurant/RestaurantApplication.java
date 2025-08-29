package com.ifanmorgan.restaurant;

import com.ifanmorgan.restaurant.entities.User;
import com.ifanmorgan.restaurant.repositories.UserRepository;
import com.ifanmorgan.restaurant.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class RestaurantApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(RestaurantApplication.class, args);
        var service = context.getBean(UserService.class);
        service.persistRelatedEntities();


    }
}
