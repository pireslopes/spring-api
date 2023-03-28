package br.com.unit.springapi.services.impl;

import br.com.unit.springapi.domain.User;
import br.com.unit.springapi.repositories.UserRepository;
import br.com.unit.springapi.services.UserService;
import br.com.unit.springapi.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;
    @Override
    public User findById(Integer id) {
        Optional<User> user = repository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("Object Not Found!"));
    }
}
