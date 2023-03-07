package br.com.manageperson.integration.controller;

import br.com.manageperson.controller.PersonController;
import br.com.manageperson.model.Person;
import br.com.manageperson.model.dto.Person.PersonDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonControllerTest {

    public static final Long ID = 1L;
    public static final String NAME = "Maria Paula";
    public static final LocalDate BIRTHDATE = LocalDate.parse("12/08/1985", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    private static final PersonDTO personDTO = new PersonDTO(ID, NAME, BIRTHDATE, new HashSet<>());
    public static final int INDEX = 0;
    public static final String OBJECT_NOT_FOUND = "Object Not Found! Id: " + ID + " Type: " + Person.class.getSimpleName();


    @Autowired
    private PersonController personController;

    @Test
    void whenFindAllThenReturnAnListOfPersonDTO() {
        List<PersonDTO> response = personController.findAll();

        assertFalse(personController.findAll().isEmpty());
        assertNotNull(response);
        assertTrue(response.size() > 0);
        assertEquals(PersonDTO.class, response.get(INDEX).getClass());
        assertEquals(ArrayList.class, response.getClass());
    }

    @Test
    void whenFindByIdThenReturnSuccess() {
        PersonDTO response = personController.findById(ID);

        assertNotNull(response);
        assertEquals(PersonDTO.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(BIRTHDATE, response.getBirthDate());
    }

    @Test
    void whenCreateThenReturnCreated() {
        PersonDTO response = personController.create(personDTO);

        assertNotNull(response);
        assertEquals(PersonDTO.class, response.getClass());
        assertNotNull(response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(BIRTHDATE, response.getBirthDate());
    }

    @Test
    void whenUpdateThenReturnSuccess() {

        PersonDTO response = personController.update(ID, personDTO);

        assertNotNull(response);
        assertNotNull(response.getClass());
        assertEquals(PersonDTO.class, response.getClass());

        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(BIRTHDATE, response.getBirthDate());
    }
}