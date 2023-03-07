package br.com.manageperson.integration.service;

import br.com.manageperson.model.Address;
import br.com.manageperson.model.Person;
import br.com.manageperson.model.dto.Address.AddressDTO;
import br.com.manageperson.model.dto.Address.AddressViewDTO;
import br.com.manageperson.model.dto.Person.PersonViewDTO;
import br.com.manageperson.service.AddressService;
import br.com.manageperson.service.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AddressServiceTest {

    public static final Long ID = 1L;
    public static final String STREET = "Laranjeiras";
    public static final String CEP = "99980000";
    public static final Integer NUMBER = 125;
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
    private AddressService addressService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void whenFindAllThenReturnAnListOfAddressDTO() {
        List<AddressDTO> response = addressService.findAll();

        assertFalse(response.isEmpty());
        assertNotNull(response);
        assertTrue(response.size() > 0);
        assertEquals(AddressDTO.class, response.get(INDEX).getClass());
        assertEquals(ID, response.get(INDEX).getId());
    }

    @Test
    void whenFindByIdThenReturnAnAddressInstance() {
        AddressDTO response = addressService.findById(ID);

        assertNotNull(response);
        assertEquals(AddressDTO.class, response.getClass());
        assertNotNull(response.getId());
        assertEquals(STREET, response.getStreet());
        assertEquals(CEP, response.getCep());
        assertEquals(NUMBER, response.getNumber());
        assertEquals(CITY, response.getCity());
        assertEquals(PERSON_VIEW_DTO.getId(), response.getPerson().getId());
    }

    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException() {
        try {
            addressService.findById(ID);
        } catch (Exception e) {
            assertEquals(ObjectNotFoundException.class, e.getClass());
            assertEquals(OBJECT_NOT_FOUND, e.getMessage());
        }
    }

    @Test
    void whenFindAllByPersonIdThenReturnAnListOfAddressViewDTO() {
        List<AddressViewDTO> response = addressService.findAllByPersonId(ID_PERSON);

        assertFalse(response.isEmpty());
        assertNotNull(response);
        assertTrue(response.size() > 0);
        assertEquals(AddressViewDTO.class, response.get(INDEX).getClass());
        assertEquals(ID, response.get(INDEX).getId());
    }

    @Test
    void create() {
        AddressDTO response = this.addressService.create(addressDTO);

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
    void whenCreateThenReturnAnObjectNotFoundException() {
        try {
            this.addressService.create(addressDTO);
        } catch (Exception e) {
            assertEquals(ObjectNotFoundException.class, e.getClass());
            assertEquals(OBJECT_NOT_FOUND_PERSON, e.getMessage());
        }
    }


    @Test
    void whenUpdateThenReturnSuccess() {
        AddressDTO response = this.addressService.update(ID, addressDTO);

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
    void whenUpdateThenReturnAnObjectNotFoundException() {
        try {
            this.addressService.update(ID, addressDTO);
        } catch (Exception e) {
            assertEquals(ObjectNotFoundException.class, e.getClass());
            assertEquals(OBJECT_NOT_FOUND, e.getMessage());
        }
    }
}