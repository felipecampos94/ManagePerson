package br.com.manageperson.integration.service;

import br.com.manageperson.model.Person;
import br.com.manageperson.model.dto.Person.PersonDTO;
import br.com.manageperson.service.PersonService;
import br.com.manageperson.service.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonServiceTest {

    public static final Long ID = 1L;
    public static final String NAME = "Maria Paula";
    public static final LocalDate BIRTHDATE = LocalDate.parse("12/08/1985", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    private static final PersonDTO personDTO = new PersonDTO(ID, NAME, BIRTHDATE, null);
    private static final Person person = new Person(ID, NAME, BIRTHDATE, null);
    public static final int INDEX = 0;
    public static final String OBJECT_NOT_FOUND = "Object Not Found! Id: " + ID + " Type: " + Person.class.getSimpleName();

    @Autowired
    private PersonService personService;

    @Test
    void whenFindAllThenReturnAnListOfPersonDTO() {
        List<PersonDTO> response = personService.findAll();

        assertFalse(response.isEmpty());
        assertNotNull(response);
        assertTrue(response.size() > 0);
        assertEquals(PersonDTO.class, response.get(INDEX).getClass());
        assertEquals(ID, response.get(INDEX).getId());

    }

    @Test
    void whenFindByIdThenReturnAnPersonInstance() {
        PersonDTO response = personService.findById(ID);

        assertNotNull(response);
        assertEquals(PersonDTO.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(BIRTHDATE, response.getBirthDate());
    }

    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException() {
        try {
            personService.findById(ID);
        } catch (Exception e) {
            assertEquals(ObjectNotFoundException.class, e.getClass());
            assertEquals(OBJECT_NOT_FOUND, e.getMessage());
        }
    }

    @Test
    void whenCreateThenReturnSuccess() {
        PersonDTO response = this.personService.create(personDTO);

        assertNotNull(response);
        assertEquals(PersonDTO.class, response.getClass());
        assertNotNull(response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(BIRTHDATE, response.getBirthDate());
    }


    @Test
    void whenUpdateThenReturnSuccess() {
        PersonDTO response = personService.update(ID, personDTO);

        assertNotNull(response);
        assertEquals(PersonDTO.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(BIRTHDATE, response.getBirthDate());
    }

    @Test
    void whenConvertPersonDTOToPerson() {
        Person response = personService.convertToEntity(personDTO);

        assertNotNull(response);
        assertEquals(NAME, response.getName());
        assertEquals(BIRTHDATE, response.getBirthDate());

    }

    @Test
    void whenConvertPersonToPersonDTO() {
        PersonDTO response = personService.convertToDTO(person);

        assertNotNull(response);
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(BIRTHDATE, response.getBirthDate());
    }


}