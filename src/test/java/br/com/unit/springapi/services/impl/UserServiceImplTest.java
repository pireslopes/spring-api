package br.com.unit.springapi.services.impl;

import br.com.unit.springapi.domain.User;
import br.com.unit.springapi.domain.dto.UserDto;
import br.com.unit.springapi.repositories.UserRepository;
import br.com.unit.springapi.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserRepository repository;

    @Mock
    private ModelMapper mapper;

    private User user;

    private UserDto dto;

    private Optional<User> optional;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = getUser();
        dto = new UserDto(getUser().getId(), getUser().getName(),getUser().getEmail(),getUser().getPassword());
        optional = Optional.of(getUser());
    }

    private static User getUser() {
        return new User(1, "joana", "joana@gmail.com", "2345");
    }

    @Test
    void whenFindByIdThenReturnUserInstance() {
        Mockito.when(repository.findById(Mockito.anyInt())).thenReturn(optional);

        var response = service.findById(getUser().getId());

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
    }

    @Test
    void whenFindByIdThenReturnObjectNotFoundException() {
        Mockito.when(repository.findById(Mockito.anyInt())).thenReturn(Optional.empty());

        try {
            service.findById(getUser().getId() + 3);
        } catch(Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals("Object Not Found!", ex.getMessage());
        }
    }

    @Test
    void findAll() {
    }

    @Test
    void add() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}