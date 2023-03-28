package br.com.unit.springapi.services;

import br.com.unit.springapi.domain.User;
import br.com.unit.springapi.domain.dto.UserDto;

import java.util.List;

public interface UserService {
    User findById(Integer id);
    List<User> findAll();
    User add(UserDto dto);
    User update(UserDto dto);
    void delete(Integer id);
}
