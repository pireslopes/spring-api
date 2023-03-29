package br.com.unit.springapi.controllers.exceptions;

import br.com.unit.springapi.controllers.UserController;
import br.com.unit.springapi.services.exceptions.DataIntegratyViolationException;
import br.com.unit.springapi.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ResourceExceptionHandlerTest {

    @InjectMocks
    private ResourceExceptionHandler handler;

    @Mock
    private UserController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenObjectNotFoundThenReturnResponseEntity() {

        var response = handler.objectNotFound(new ObjectNotFoundException("message"), new MockHttpServletRequest());

        assertNotNull(response);

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());

    }

    @Test
    void dataIntegratyViolationException() {
        var response = handler.dataIntegratyViolationException(new DataIntegratyViolationException("message"), new MockHttpServletRequest());

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
    }
}