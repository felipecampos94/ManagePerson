package br.com.manageperson.controller;

import br.com.manageperson.model.dto.Address.AddressDTO;
import br.com.manageperson.model.dto.Address.AddressViewDTO;
import br.com.manageperson.service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping
    public List<AddressDTO> findAll() {
        return addressService.findAll();
    }

    @GetMapping("/{id}")
    public AddressDTO findById(@PathVariable Long id) {
        return addressService.findById(id);
    }

    @GetMapping("/person/{id}")
    public List<AddressViewDTO> findAllByPersonId(@PathVariable Long id) {
        return addressService.findAllByPersonId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AddressDTO create(@Valid @RequestBody AddressDTO addressDTO) {
        return addressService.create(addressDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AddressDTO update(@PathVariable Long id, @Valid @RequestBody AddressDTO addressDTO) {
        return addressService.update(id, addressDTO);
    }
}
