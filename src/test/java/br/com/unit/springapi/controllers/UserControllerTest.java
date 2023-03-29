package br.com.unit.springapi.controllers;

import br.com.unit.springapi.domain.User;
import br.com.unit.springapi.domain.dto.UserDto;
import br.com.unit.springapi.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserControllerTest {

    @InjectMocks
    private UserController controller;

    @Mock
    private UserServiceImpl service;

    @Mock
    private ModelMapper mapper;

    private User user;

    private UserDto dto;

    private Optional<User> optional;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        setInstanceMock();
    }

    @Test
    void whenFindByIdThenReturnUserDto() {
        Mockito.when(service.findById(Mockito.anyInt())).thenReturn(user);
        Mockito.when(mapper.map(Mockito.any(), Mockito.any())).thenReturn(dto);

        var response = controller.FindById(getUser().getId());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
    }

    @Test
    void findAll() {
    }

    @Test
    void addUser() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void setInstanceMock() {
        user = getUser();
        dto = new UserDto(getUser().getId(), getUser().getName(),getUser().getEmail(),getUser().getPassword());
        optional = Optional.of(getUser());
    }

    private static User getUser() {
        return new User(1, "joana", "joana@gmail.com", "2345");
    }
}