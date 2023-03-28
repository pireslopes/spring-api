package br.com.unit.springapi.controllers;

import br.com.unit.springapi.domain.User;
import br.com.unit.springapi.domain.dto.UserDto;
import br.com.unit.springapi.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/user")
public class UserController {


    private final ModelMapper mapper;

    private final UserService service;

    public UserController(ModelMapper mapper, UserService service) {
        this.mapper = mapper;
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto> FindById(@PathVariable Integer id) {
        var user = service.findById(id);
        return ResponseEntity.ok().body(mapper.map(user, UserDto.class));
    }

    @RequestMapping
    public ResponseEntity<List<UserDto>> findAll() {
        var userList = service.findAll();
        var userDtoList = userList.stream().map(x -> mapper.map(x, UserDto.class)).collect(Collectors.toList());

        return ResponseEntity.ok().body(userDtoList);
    }

    @PostMapping
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto dto) {
        var user = service.add(dto);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDto> update(@PathVariable Integer id, @RequestBody UserDto dto) {
        dto.setId(id);
        User user = service.update(dto);
        return ResponseEntity.ok().body(mapper.map(user, UserDto.class));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<UserDto> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
