package xyz.ther.boot.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    public String getUserById(Integer id) {
        System.out.println("get...");
        return "user";
    }

    public void deleteUserById(Integer id) {
        System.out.println("delete...");
    }

    public String sayHello(String name) {
        return "Hello " + name + " !";
    }
}
