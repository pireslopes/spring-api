package br.com.unit.springapi.controllers;

import br.com.unit.springapi.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @GetMapping(value = "/{id}")
    public ResponseEntity FindById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(new User(1, "test", "test@gmail.com", "45tr6y"));
    }
}
