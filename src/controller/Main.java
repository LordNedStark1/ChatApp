package controller;

import model.users.UserInterface;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import services.UserServiceImpl;

import java.util.ArrayList;

@SpringBootApplication
public class Main {
//    UserServiceController userServiceController = new UserServiceController();
    public static void main(String... params) {
        SpringApplication.run(Main.class, params);

    }

}
