package xyz.ther.boot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/admin/hello")
    public String admin() {
        return "Hello admin!";
    }

    @GetMapping("/user/hello")
    public String user() {
        return "Hello user!";
    }

    @GetMapping("/db/hello")
    public String dba() {
        return "Hello dba!";
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello!";
    }

    @GetMapping("/hello-name")
    public String hello(String name) {
        return "Hello " + name + " !";
    }

    @GetMapping("/hello2")
    public String hello2() {
        return "Hello2";
    }
}
