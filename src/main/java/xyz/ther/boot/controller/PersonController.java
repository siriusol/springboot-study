package xyz.ther.boot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.ther.boot.pojo.Person;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

@RestController
@RequestMapping("/api")
@Validated
public class PersonController {

    @PostMapping("/person")
    public ResponseEntity<Person> postPerson(@RequestBody @Valid Person person) {
        return ResponseEntity.ok().body(person);
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<Integer> getPersonById(
            @Valid @PathVariable("id") @Max(value = 5, message = "超过 id 的范围了") Integer id) {
        return ResponseEntity.ok().body(id);
    }

    @PutMapping("/person")
    public ResponseEntity<String> getPersonByName(@Valid @RequestParam("name") @Size(max = 6,message = "超过 name 的范围了") String name) {
        return ResponseEntity.ok().body(name);
    }
}
