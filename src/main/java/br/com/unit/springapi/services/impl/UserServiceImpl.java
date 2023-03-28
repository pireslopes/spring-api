package br.com.unit.springapi.services.impl;

import br.com.unit.springapi.domain.User;
import br.com.unit.springapi.domain.dto.UserDto;
import br.com.unit.springapi.repositories.UserRepository;
import br.com.unit.springapi.services.UserService;
import br.com.unit.springapi.services.exceptions.DataIntegratyViolationException;
import br.com.unit.springapi.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    private final ModelMapper mapper;

    private final UserRepository repository;

    public UserServiceImpl(ModelMapper mapper, UserRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public User findById(Integer id) {
        Optional<User> user = repository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("Object Not Found!"));
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User add(UserDto dto) {
        findByEmail(dto);
        return repository.save(mapper.map(dto, User.class));
    }

    @Override
    public User update(UserDto dto) {
        findByEmail(dto);
        return repository.save(mapper.map(dto, User.class));
    }

    private void findByEmail(UserDto dto) {
        Optional<User> user = repository.findByEmail(dto.getEmail());
        if (user.isPresent() && !user.get().getId().equals(dto.getId())) {
            throw new DataIntegratyViolationException("E-mail already registered!");
        }
    }
}
