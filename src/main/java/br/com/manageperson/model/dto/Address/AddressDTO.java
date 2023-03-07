package br.com.manageperson.model.dto.Address;

import br.com.manageperson.model.dto.Person.PersonViewDTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddressDTO {

    private Long id;

    @NotNull(message = "The Street is required.")
    private String street;

    @NotNull(message = "The CEP is required.")
    private String cep;

    @NotNull(message = "The Numer is required.")
    private Integer number;

    @NotNull(message = "The City is required.")
    private String city;

    private Boolean mainAddress = false;

    private PersonViewDTO person;
}
