package br.com.manageperson.service;

import br.com.manageperson.model.Address;
import br.com.manageperson.model.Person;
import br.com.manageperson.repository.AddressRepository;
import br.com.manageperson.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class DBService {

    private final PersonRepository personRepository;

    private final AddressRepository addressRepository;

    public void instantiateDatabase() {

        Person person = new Person(null, "Maria Paula", LocalDate.parse("12/08/1985", DateTimeFormatter.ofPattern("dd/MM/yyyy")), new HashSet<>());
        Address address = new Address(null, "Laranjeiras", "99980000", 265, "São Paulo", false, person);
        person.getAddresses().addAll(Arrays.asList(address));

        personRepository.saveAll(Arrays.asList(person));
        addressRepository.saveAll(Arrays.asList(address));
    }
}
