package br.com.manageperson.integration.controller.exception;

import br.com.manageperson.controller.exception.ControllerExceptionHandler;
import br.com.manageperson.controller.exception.StandardError;
import br.com.manageperson.service.exceptions.DataIntegrityViolationException;
import br.com.manageperson.service.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ControllerExceptionHandlerTest {

    public static final String OBJECT_NOT_FOUND = "Object Not Found!";

    public static final String REQUIRED = "Attribute Is Required";

    @Autowired
    private ControllerExceptionHandler controllerExceptionHandler;

    @BeforeEach
    void setUp() {
    }

    @Test
    void objectNotFound() {
        ResponseEntity<StandardError> response = controllerExceptionHandler.objectNotFound(
                new ObjectNotFoundException(OBJECT_NOT_FOUND),
                new MockHttpServletRequest()
        );

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals(404, response.getBody().getStatus());
    }

    @Test
    void dataIntegrityViolation() {
        ResponseEntity<StandardError> response = controllerExceptionHandler.dataIntegrityViolationException(
                new DataIntegrityViolationException(REQUIRED),
                new MockHttpServletRequest()
        );

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals(400, response.getBody().getStatus());
    }
}