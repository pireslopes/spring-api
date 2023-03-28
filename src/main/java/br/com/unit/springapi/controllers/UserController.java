package br.com.unit.springapi.controllers;

import br.com.unit.springapi.domain.dto.UserDto;
import br.com.unit.springapi.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UserService service;
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto> FindById(@PathVariable Integer id) {
        var user = service.findById(id);
        return ResponseEntity.ok().body(mapper.map(user, UserDto.class));
    }
}
