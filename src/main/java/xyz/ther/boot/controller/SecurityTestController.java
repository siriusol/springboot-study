package xyz.ther.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.ther.boot.service.SecurityMethodService;

@RestController
@RequestMapping("/security")
public class SecurityTestController {

    @Autowired
    private SecurityMethodService service;

    @GetMapping("/admin")
    public String admin() {
        return service.admin();
    }

    @GetMapping("/dba")
    public String dba() {
        return service.dba();
    }

    @GetMapping("/user")
    public String user() {
        return service.user();
    }
}
