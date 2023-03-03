package br.com.manageperson.service;

import br.com.manageperson.model.Person;
import br.com.manageperson.model.dto.Person.PersonDTO;
import br.com.manageperson.repository.PersonRepository;
import br.com.manageperson.service.exceptions.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    private final ModelMapper modelMapper;

    public List<PersonDTO> findAll() {
        return personRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) {
        Optional<Person> person = personRepository.findById(id);
        return convertToDTO(person.orElseThrow(() -> new ObjectNotFoundException("Object Not Found! Id: " + id + " Type: " + Person.class.getSimpleName())));
    }

    public PersonDTO create(PersonDTO personDTO) {
        personDTO.setId(null);
        var person = convertToEntity(personDTO);
        return convertToDTO(personRepository.save(person));
    }

    public PersonDTO update(Long id, PersonDTO personDTO) {
        var currentPersonDTO = this.findById(id);
        updateData(currentPersonDTO, personDTO);
        var person = convertToEntity(currentPersonDTO);
        return convertToDTO(personRepository.save(person));
    }

    private void updateData(PersonDTO currentPersonDTO, PersonDTO personDTO) {
        currentPersonDTO.setName(personDTO.getName());
        currentPersonDTO.setBirthDate(personDTO.getBirthDate());
    }

    public Person convertToEntity(PersonDTO personDTO) {
        return modelMapper.map(personDTO, Person.class);
    }

    public PersonDTO convertToDTO(Person person) {
        return modelMapper.map(person, PersonDTO.class);
    }
}
