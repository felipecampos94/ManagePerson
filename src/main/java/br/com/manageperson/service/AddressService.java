package br.com.manageperson.service;

import br.com.manageperson.model.Address;
import br.com.manageperson.model.dto.Address.AddressDTO;
import br.com.manageperson.model.dto.Address.AddressViewDTO;
import br.com.manageperson.repository.AddressRepository;
import br.com.manageperson.service.exceptions.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;

    private final PersonService personService;
    private final ModelMapper modelMapper;

    public List<AddressDTO> findAll() {
        return addressRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public AddressDTO findById(Long id) {
        Optional<Address> address = addressRepository.findById(id);
        return convertToDTO(address.orElseThrow(() ->
                new ObjectNotFoundException("Object Not Found! Id: " + id + " Type: " + Address.class.getSimpleName())));
    }

    public List<AddressViewDTO> findAllByPersonId(Long id) {
        var addressDTO = this.findById(id);
        return addressRepository.findAllByPersonId(addressDTO.getId())
                .stream().map(this::convertToAddressViewDTO).collect(Collectors.toList());
    }

    public AddressDTO create(AddressDTO addressDTO) {
        addressDTO.setId(null);
        verfifyForeignKey(addressDTO);
        var address = convertToEntity(addressDTO);
        return convertToDTO(addressRepository.save(address));
    }

    public AddressDTO update(Long id, AddressDTO addressDTO) {
        var currentAddressDTO = this.findById(id);
        updateData(currentAddressDTO, addressDTO);
        verfifyForeignKey(currentAddressDTO);
        var address = convertToEntity(currentAddressDTO);
        return convertToDTO(addressRepository.save(address));
    }

    private void updateData(AddressDTO currentAddressDTO, AddressDTO addressDTO) {
        currentAddressDTO.setStreet(addressDTO.getStreet());
        currentAddressDTO.setNumber(addressDTO.getNumber());
        currentAddressDTO.setCep(addressDTO.getCep());
        currentAddressDTO.setCity(addressDTO.getCity());
        currentAddressDTO.setMainAddress(addressDTO.getMainAddress());
        currentAddressDTO.setPerson(addressDTO.getPerson());
    }

    private void verfifyForeignKey(AddressDTO addressDTO) {
        this.personService.findById(addressDTO.getPerson().getId());
    }

    private Address convertToEntity(AddressDTO addressDTO) {
        return modelMapper.map(addressDTO, Address.class);
    }

    private AddressDTO convertToDTO(Address address) {
        return modelMapper.map(address, AddressDTO.class);
    }

    private AddressViewDTO convertToAddressViewDTO(Address address) {
        return modelMapper.map(address, AddressViewDTO.class);
    }

}
