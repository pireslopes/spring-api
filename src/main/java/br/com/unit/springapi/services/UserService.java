package br.com.unit.springapi.services;

import br.com.unit.springapi.domain.User;

public interface UserService {
    User findById(Integer id);
}
