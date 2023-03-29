package br.com.unit.springapi.services.impl;

import br.com.unit.springapi.domain.User;
import br.com.unit.springapi.domain.dto.UserDto;
import br.com.unit.springapi.repositories.UserRepository;
import br.com.unit.springapi.services.exceptions.DataIntegratyViolationException;
import br.com.unit.springapi.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

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
    void whenFindAllThenReturnAnListOfUsers() {
        var current = List.of(getUser());
        Mockito.when(repository.findAll()).thenReturn(current);

        var expected = service.findAll();

        assertTrue(expected.size() > 0);

        assertIterableEquals(expected, current);
    }

    @Test
    void whenAddWithCorrectInputThenReturnSuccess() {
        Mockito.when(repository.save(Mockito.any())).thenReturn(user);

        var response = service.add(dto);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
    }

    @Test
    void whenAddWithExistUserThenReturnDataIntegratyViolationException() {
        Mockito.when(repository.findByEmail(Mockito.any())).thenReturn(optional);

        try {
            dto.setId(3);
            service.add(dto);
        } catch (Exception ex) {
            assertEquals(DataIntegratyViolationException.class, ex.getClass());
            assertEquals("E-mail already registered!", ex.getMessage());
        }
    }

    @Test
    void whenUpdateWithCorrectInputThenReturnSuccess() {
        Mockito.when(repository.save(Mockito.any())).thenReturn(user);

        var response = service.update(dto);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
    }

    @Test
    void whenUpdateWithExistUserThenReturnDataIntegratyViolationException() {
        Mockito.when(repository.findByEmail(Mockito.any())).thenReturn(optional);

        try {
            dto.setId(3);
            service.update(dto);
        } catch (Exception ex) {
            assertEquals(DataIntegratyViolationException.class, ex.getClass());
            assertEquals("E-mail already registered!", ex.getMessage());
        }
    }

    @Test
    void whenDeleteWithExistsUserThenExecuteSuccess() {
        Mockito.when(repository.findById(Mockito.anyInt())).thenReturn(optional);
        Mockito.doNothing().when(repository).deleteById(Mockito.anyInt());
        service.delete(getUser().getId());
        Mockito.verify(repository, Mockito.times(1)).deleteById(Mockito.anyInt());
    }

    @Test
    void whenDeleteWithNotExistsUserThenThrowObjectNotFoundException() {
        Mockito.when(repository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        try {
            service.delete(getUser().getId() + 9);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals("Object Not Found!", ex.getMessage());
        }
    }
}