package br.com.manageperson.integration.controller;

import br.com.manageperson.controller.AddressController;
import br.com.manageperson.model.Address;
import br.com.manageperson.model.Person;
import br.com.manageperson.model.dto.Address.AddressDTO;
import br.com.manageperson.model.dto.Address.AddressViewDTO;
import br.com.manageperson.model.dto.Person.PersonDTO;
import br.com.manageperson.model.dto.Person.PersonViewDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AddressControllerTest {

    public static final Long ID = 1L;
    public static final String STREET = "Laranjeiras";
    public static final String CEP = "99980000";
    public static final Integer NUMBER = 265;
    public static final String CITY = "São Paulo";
    public static final Boolean MAIN_ADDRESS = false;
    public static final Long ID_PERSON = 1L;
    public static final String NAME = "Marcos Paulo";
    public static final PersonViewDTO PERSON_VIEW_DTO = new PersonViewDTO(ID_PERSON, NAME);
    private static final AddressDTO addressDTO = new AddressDTO(ID, STREET, CEP, NUMBER, CITY, MAIN_ADDRESS, PERSON_VIEW_DTO);
    public static final int INDEX = 0;
    public static final String OBJECT_NOT_FOUND = "Object Not Found! Id: " + ID + " Type: " + Address.class.getSimpleName();
    public static final String OBJECT_NOT_FOUND_PERSON = "Object Not Found! Id: " + ID + " Type: " + Person.class.getSimpleName();

    @Autowired
    private AddressController addressController;

    @Test
    void whenFindAllThenReturnAnListOfAddressDTO() {
        List<AddressDTO> response = addressController.findAll();

        assertFalse(addressController.findAll().isEmpty());
        assertNotNull(response);
        assertTrue(response.size() > 0);
        assertEquals(AddressDTO.class, response.get(INDEX).getClass());
        assertEquals(ArrayList.class, response.getClass());
    }

    @Test
    void whenFindByIdThenReturnSuccess() {
        AddressDTO response = addressController.findById(ID);

        assertNotNull(response);
        assertEquals(AddressDTO.class, response.getClass());
        assertNotNull(response.getId());
        assertEquals(STREET, response.getStreet());
        assertEquals(CEP, response.getCep());
        assertEquals(NUMBER, response.getNumber());
        assertEquals(CITY, response.getCity());
        assertEquals(PERSON_VIEW_DTO.getId(), response.getPerson().getId());
        assertNotNull(PERSON_VIEW_DTO);
    }

    @Test
    void whenFindAllByPersonIdThenReturnAnListOfAddressViewDTO() {
        List<AddressViewDTO> response = addressController.findAllByPersonId(ID_PERSON);

        assertFalse(response.isEmpty());
        assertNotNull(response);
        assertTrue(response.size() > 0);
        assertEquals(AddressViewDTO.class, response.get(INDEX).getClass());
        assertEquals(ID, response.get(INDEX).getId());
    }

    @Test
    void whenCreateThenReturnCreated() {
        AddressDTO response = addressController.create(addressDTO);

        assertNotNull(response);
        assertEquals(AddressDTO.class, response.getClass());
        assertNotNull(response.getId());
        assertEquals(STREET, response.getStreet());
        assertEquals(CEP, response.getCep());
        assertEquals(NUMBER, response.getNumber());
        assertEquals(CITY, response.getCity());
        assertEquals(PERSON_VIEW_DTO.getId(), response.getPerson().getId());
        assertNotNull(PERSON_VIEW_DTO);
    }

    @Test
    void whenUpdateThenReturnSuccess() {
        AddressDTO response = addressController.update(ID, addressDTO);

        assertNotNull(response);
        assertEquals(AddressDTO.class, response.getClass());
        assertNotNull(response.getId());
        assertEquals(STREET, response.getStreet());
        assertEquals(CEP, response.getCep());
        assertEquals(NUMBER, response.getNumber());
        assertEquals(CITY, response.getCity());
        assertEquals(PERSON_VIEW_DTO.getId(), response.getPerson().getId());
        assertNotNull(PERSON_VIEW_DTO);
    }
}